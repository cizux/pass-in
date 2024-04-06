package rocketseat.com.passin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.checkin.Checkin;
import rocketseat.com.passin.dto.attendee.AttendeeDetails;
import rocketseat.com.passin.dto.attendee.AttendeesListResponseDTO;
import rocketseat.com.passin.repository.AttendeeRepository;
import rocketseat.com.passin.repository.CheckinRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {
	
	private final AttendeeRepository attendeeRepository;
	
	private final CheckinRepository checkinRepository;
	
	public List<Attendee> getAllAttendeesFromEvent(String eventId) {
	return	this.attendeeRepository.findByEventId(eventId);
	}
	
	
	public AttendeesListResponseDTO getEventsAttendee(String eventId) {
		
		List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);
		
		List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
			Optional<Checkin> checkin = this.checkinRepository.findByAttendeeId(attendee.getId());
			//LocalDateTime checkedInAt = checkin.isPresent() ? checkin.get().getCreatedAt() : null;
			LocalDateTime checkedInAt = checkin.<LocalDateTime>map(Checkin::getCreatedAt).orElse(null);
			
			return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
			
		}).toList();
		
		return new AttendeesListResponseDTO(attendeeDetailsList);
		
				
	}

}
