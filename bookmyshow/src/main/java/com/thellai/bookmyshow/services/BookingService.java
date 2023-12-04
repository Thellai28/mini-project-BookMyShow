package com.thellai.bookmyshow.services;

//@Service
//public class BookingService {

//    private UserRepository userRepository;
//    private ShowRepository showRepository;
//    private ShowSeatRepository showSeatRepository;
//
//    private PricingService pricingService;
//
//    private BookingRepository bookingRepository;
//
//    public BookingService( UserRepository userRepository,
//                           ShowRepository showRepository,
//                           ShowSeatRepository showSeatRepository,
//                           PricingService pricingService,
//                           BookingRepository bookingRepository ) {
//        this.userRepository = userRepository;
//        this.showRepository = showRepository;
//        this.showSeatRepository = showSeatRepository;
//        this.pricingService = pricingService;
//        this.bookingRepository = bookingRepository;
//    }
//
//    @Transactional(isolation = Isolation.SERIALIZABLE)
//    public Booking bookMovie(Long userId, List<Long> seatsIds, Long showId) {
//        Optional<User> userOptional = userRepository.findById( userId );
//
//        if( userOptional.isEmpty() ){
//            throw new RuntimeException( "userNotFound" );
//        }
//        User bookedBy = userOptional.get();
//
//        Optional<Show> showOptional = showRepository.findById( showId );
//        if( showOptional.isEmpty() ){
//            throw new RuntimeException( "ShowNotFound" );
//        }
//        Show bookedShow = showOptional.get();
//
//        List<ShowSeat> showSeats = showSeatRepository.findAllById( seatsIds );
//
//
//        for( ShowSeat showSeat : showSeats ){
//            boolean isSeatAvailable = showSeat.getShowSeatStatus().equals( ShowSeatStatus.AVAILABLE );
//            boolean isTimeLimitExceeded = ( showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
//                                            Duration.between( showSeat.getBookedAt().toInstant(),
//                                                    new Date().toInstant() ).toMinutes() > 15
//            );
//            if( !(isSeatAvailable || isTimeLimitExceeded) ){
//                throw new RuntimeException("SeatNotAvailableException");
//            }
//        }
//
//        List<ShowSeat> bookedShowSeats = new ArrayList<>();
//        for( ShowSeat showSeat : showSeats ){
//            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
//            bookedShowSeats.add( showSeatRepository.save( showSeat ) );
//        }
//
//        Booking booking  = new Booking();
//        booking.setBookingStatus(BookingStatus.PENDING);
//        booking.setShowSeat( bookedShowSeats );
//        booking.setUser(bookedBy);
//        booking.setBookedAt( new Date());
//        booking.setShow(bookedShow);
//        booking.setAmount( pricingService.calculatePrice(bookedShowSeats, bookedShow));
//        Booking savedBooking = bookingRepository.save( booking );
//
//        return savedBooking;
//    }
//}

