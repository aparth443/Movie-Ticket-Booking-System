package com.example.bookmyshow.Services;

import com.example.bookmyshow.Converters.TicketConverter;
import com.example.bookmyshow.EntryDtos.CancelTicketDto;
import com.example.bookmyshow.EntryDtos.TicketEntryDto;
import com.example.bookmyshow.Models.ShowEntity;
import com.example.bookmyshow.Models.ShowSeatEntity;
import com.example.bookmyshow.Models.TicketEntity;
import com.example.bookmyshow.Models.UserEntity;
import com.example.bookmyshow.Repositories.ShowRepository;
import com.example.bookmyshow.Repositories.TicketRepository;
import com.example.bookmyshow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public String bookTicket(TicketEntryDto ticketEntryDto) throws Exception {

        TicketEntity ticketEntity = TicketConverter.convertTicketEntryDtoToTicketEntity(ticketEntryDto);

        boolean isValidRequest = checkValidityOfSelectedSeats(ticketEntryDto);

        if(isValidRequest == false){
            throw new Exception("Selected Seats already booked.");
        }
        int showId = ticketEntryDto.getShowId();
        ShowEntity showEntity = showRepository.findById(showId).get();
        List<ShowSeatEntity> showSeatEntityList = showEntity.getShowSeatEntityList();
        List<String> selectedSeats = ticketEntryDto.getRequestedSeats();

        //calculating total price
        int totalPrice = 0;
        for(ShowSeatEntity showSeatEntity: showSeatEntityList){
            if(selectedSeats.contains(showSeatEntity.getSeatNo())){
                totalPrice += showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }
        ticketEntity.setTotalAmount(totalPrice);
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setTheatreName(showEntity.getTheatreEntity().getName());

        String bookedSeats = "";
        for(String seats : selectedSeats){
            bookedSeats += seats + ",";
        }
        ticketEntity.setBookedSeats(bookedSeats);

        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();

        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        ticketRepository.save(ticketEntity);

        List<TicketEntity> ticketEntityList = showEntity.getTicketEntityList();
        ticketEntityList.add(ticketEntity);
        showEntity.setTicketEntityList(ticketEntityList);

        showRepository.save(showEntity);

        List<TicketEntity> ticketEntityList1 = userEntity.getTicketEntityList();
        ticketEntityList1.add(ticketEntity);
        userEntity.setTicketEntityList(ticketEntityList1);
        userRepository.save(userEntity);

        String body = "Hi this is to confirm your booking for seat No "+bookedSeats +"for the movie : " + ticketEntity.getMovieName();


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("agrawalparth475@gmail.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);

        return "Ticket booked Successfully!";
    }

    public boolean checkValidityOfSelectedSeats(TicketEntryDto ticketEntryDto){
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();

        List<ShowSeatEntity> showSeatEntityList = showEntity.getShowSeatEntityList();
        List<String> selectedSeats = ticketEntryDto.getRequestedSeats();

            for(ShowSeatEntity showSeatEntity: showSeatEntityList){
                String seat = showSeatEntity.getSeatNo();
                if(selectedSeats.contains(seat)){
                    if(showSeatEntity.isBooked() == true){
                        return false;
                    }
                }
            }
            return true;
    }

    public String cancelTicket(CancelTicketDto cancelTicketDto) throws Exception{
        TicketEntity ticketEntity = ticketRepository.findById(cancelTicketDto.getTicketId()).get();
        String bookedTickets = ticketEntity.getBookedSeats();
        String [] str = bookedTickets.split(",");
        List<String> bookedSeats = Arrays.asList(str);
        List<String> toBeCancelled = cancelTicketDto.getCancelTicketList();
        for(String seat: toBeCancelled){
            if(!bookedSeats.contains(seat)){
                throw new Exception("Selected seat is not booked by you. Please select valid seat.");
            }
        }
        List<ShowSeatEntity> showSeatEntityList = ticketEntity.getShowEntity().getShowSeatEntityList();
        HashSet<String> deletedSeats = new HashSet<>();
        int cancelledTicketPrice = 0;
        for(ShowSeatEntity seat: showSeatEntityList){
            if(toBeCancelled.contains(seat.getSeatNo())){
                deletedSeats.add(seat.getSeatNo());
                cancelledTicketPrice += seat.getPrice();
                seat.setBooked(false);
                seat.setBookedAt(null);
            }
        }

        String newBookedSeats = "";
        for(String ticket: bookedSeats){
            if(!deletedSeats.contains(ticket)){
                if(!newBookedSeats.isEmpty()){
                    newBookedSeats += ",";
                }
                newBookedSeats += ticket;
            }
        }
        String deleted = "";
        for(String s: deletedSeats){
            deleted += s;
        }
        int cancellationCharge = deletedSeats.size()*50;
        int amountToBeRefunded = cancelledTicketPrice - cancellationCharge;
        int newPrice = ticketEntity.getTotalAmount() - cancelledTicketPrice;
        ticketEntity.setTotalAmount(newPrice);
        UserEntity userEntity = ticketEntity.getUserEntity();

        String body ="Hi " + userEntity.getName() + ",\n" + "This is to confirm that your booking for ticket " + ticketEntity.getTicketId() + "has been cancelled successfully.\n Cancelled Seats:" + deleted
                 + "\n Cancellation Charges " + cancellationCharge + "\nAmount to be refunded: " + amountToBeRefunded  + "\nHave a great day!";

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("agrawalparth475@gmail.com");
        mimeMessageHelper.setTo(userEntity.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Cancellation of Movie tickets ");

        javaMailSender.send(mimeMessage);


        if(newBookedSeats.isEmpty()){
            ticketRepository.delete(ticketEntity);
            userRepository.save(userEntity);
            return "Ticket has been cancelled Successfully.";
        }
        ticketEntity.setBookedSeats(newBookedSeats);
        userRepository.save(userEntity);
        return "Ticket has been cancelled successfully.";
    }
}
