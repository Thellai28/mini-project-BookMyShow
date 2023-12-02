package com.thellai.bookmyshow.controllers;

import com.thellai.bookmyshow.models.Movie;
import com.thellai.bookmyshow.models.Show;
import com.thellai.bookmyshow.models.ShowSeat;
import com.thellai.bookmyshow.models.ShowSeatStatus;
import com.thellai.bookmyshow.repositories.MovieRepository;
import com.thellai.bookmyshow.repositories.ShowRepository;
import com.thellai.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class BookingController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowRepository showRepository;
    private Scanner sc = new Scanner( System.in );


    @Autowired
    private ShowSeatRepository showSeatRepository;

    private Map<Integer, ShowSeat> showSeatsCacheMap = new HashMap<>();



    //<-------------------------------------MODEL METHODS----------------------------->


    public void bookMovie(){

        Movie selectedMovie = displayMoviesForSelection();
        Show showRunningThisMovie = showRepository.findByMovie(selectedMovie);
        List<ShowSeat> seatsInThisShow = fetchAllSeatsInShow(showRunningThisMovie);

        List<ShowSeat> finalSelectedSeatsForBooking;
        boolean moveToPaymentSection = false;
        boolean isUserDiscardedSelection = false;

        while( !moveToPaymentSection ){
            int seatsAvailableCount = displaySeatsForSelection(seatsInThisShow);
            finalSelectedSeatsForBooking = selectSeatsForBooking(seatsAvailableCount);

            if( finalSelectedSeatsForBooking.size() == 0 ){
                // sometimes, user won't select seats, they'll be checking the seat availability :
                isUserDiscardedSelection = true;
                break;
            }
            boolean isSeatSelectionFinal = BlockedSeatFinalVerification( finalSelectedSeatsForBooking );

            if( !isSeatSelectionFinal ){
                revertSelectedSeatStatus( finalSelectedSeatsForBooking );
                moveToPaymentSection = false;
            }else moveToPaymentSection = true;
        }
    }


    public void createSpaceInTerminal(int lines){
        for( int i = 0; i < lines; i++ ){
            System.out.println();
        }
    }



    public List<ShowSeat> fetchAllSeatsInShow( Show show ){
        List<ShowSeat> seatsInThisSHow = showSeatRepository.findAllByShow(show);
        return seatsInThisSHow;
    }






    public Movie displayMoviesForSelection(){
        System.out.println("Choose the movie");
        List<Movie> movies = movieRepository.findAll();
        for( int i = 0; i < movies.size(); i++ ){
            System.out.println(i+1 + " : " + movies.get(i).getName() );
        }
        int movieSelection = sc.nextInt();
        Movie chosenMovie = movies.get( movieSelection - 1);
        return chosenMovie;
    }





    public int displaySeatsForSelection( List<ShowSeat> showSeats ){

        System.out.println(" Select seats from the below layout ");
        createSpaceInTerminal(3); // creates 3 line in terminal :

        int idx = 0; // to get elements from the showSeats list :
        int availableSeatCount = 0;

        for( int row = 0; row < 3; row++ ){ // im hardcoding the size of rows & col
            for( int col = 0; col < 4; col ++  ){

                // Getting the seat no & storing in map.
                ShowSeat currShowSeat = showSeats.get( idx++ );
                int seatNo = currShowSeat.getSeat().getSeatNO();
                showSeatsCacheMap.put( seatNo, currShowSeat);

                ShowSeatStatus showSeatStatus = currShowSeat.getShowSeatStatus();
                if( showSeatStatus.equals( ShowSeatStatus.AVAILABLE) ){
                    availableSeatCount++ ;
                }

                String seatDetails = "---- set " + seatNo +" : "  +currShowSeat.getShowSeatStatus() + "  ----";
                System.out.print( seatDetails);
            }
            createSpaceInTerminal(4); // creates 4 line in terminal.
        }
        return availableSeatCount;
    }





    private List<ShowSeat> selectSeatsForBooking( int seatsAvailableCount ){
        boolean isSelectionDone = false;
        List<ShowSeat> selectedShowSeats =  new ArrayList<>();

        while( seatsAvailableCount > 0 && !isSelectionDone ){
            createSpaceInTerminal(2);
            String selectionMessage = "Do you want to select Seats ?\n"+
                                        "0.No \n" +
                                        "1.Yes";
            System.out.println( selectionMessage );
            createSpaceInTerminal(2);
            int response = sc.nextInt();

            if( response == 1 ){

                System.out.println( "Please enter the seat number here " );
                int seatNo = sc.nextInt();
                ShowSeat currSelectedSeat = showSeatsCacheMap.get(seatNo);
                if( currSelectedSeat.getShowSeatStatus().equals( ShowSeatStatus.AVAILABLE) ){

                    seatsAvailableCount--;
                    selectedShowSeats.add( showSeatsCacheMap.get(seatNo) );
                    currSelectedSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
                    // updating the status of seat or else user can select same seat more than once.

                    System.out.println(
                            "seat No " + seatNo + " is blocker for booking, remaining available seats "
                                    + seatsAvailableCount );
                    createSpaceInTerminal(2);
                }else{
                    System.out.println("sorry, the selected seat is not available");
                }

            }else isSelectionDone = true;
        }
        showSeatsCacheMap.clear(); // once the selection is done, we clear the cache :
        //System.out.println("line 160 : selected seat count " + selectedShowSeats.size() );
        return selectedShowSeats;
    }





    private boolean BlockedSeatFinalVerification( List<ShowSeat> finalSelectedSeatsForBooking ){

        createSpaceInTerminal(2);
        System.out.println("selected Seat count = " + finalSelectedSeatsForBooking.size()
                +". Please find the detailed list below" );

        for( int i = 0; i < finalSelectedSeatsForBooking.size(); i++ ){
            ShowSeat currShowSeat = finalSelectedSeatsForBooking.get(i);
            createSpaceInTerminal(1);
            System.out.println( i+1 + ". seat number : " + currShowSeat.getSeat().getSeatNO() );
        }

        String confirmationMessage = "Please select yes, if the blocked seats are correct\n"
                                        + "0.No\n"
                                        +"1.Yes";

        System.out.println(confirmationMessage);
        int response = sc.nextInt();
        if( response == 1 ) return  true;
        return false;
    }



    private void revertSelectedSeatStatus( List<ShowSeat> finalSelectedSeatsForBooking ){
        for( ShowSeat currShowSeat : finalSelectedSeatsForBooking ){
            currShowSeat.setShowSeatStatus( ShowSeatStatus.AVAILABLE);
        }
    }

}
