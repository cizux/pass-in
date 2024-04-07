package rocketseat.com.passin.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.checkin.Checkin;
import rocketseat.com.passin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import rocketseat.com.passin.repository.CheckinRepository;

@Service
@RequiredArgsConstructor
public class CheckInService {

	private final CheckinRepository checkinRepository;
	
	public void RegisterCheckIn(Attendee attendee) {
		
		this.verifyCheckInExists(attendee.getId());
		
		Checkin newCheckIn = new Checkin();
		newCheckIn.setAttendee(attendee);
		newCheckIn.setCreatedAt(LocalDateTime.now());
		
		this.checkinRepository.save(newCheckIn);
	}
	
	
	private void verifyCheckInExists(String attendeeId) {
	 Optional<Checkin> ischeckedIn = this.getCheckIn(attendeeId);
	
	 if(ischeckedIn.isPresent()) throw new CheckInAlreadyExistsException("Attendee already check in");
	}
	
	public Optional<Checkin> getCheckIn(String attendeeId){
		return this.checkinRepository.findByAttendeeId(attendeeId);
		
	}
}
