package rocketseat.com.passin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import rocketseat.com.passin.dto.attendee.AttendeeIdDTO;
import rocketseat.com.passin.dto.attendee.AttendeeRequestDTO;
import rocketseat.com.passin.dto.attendee.AttendeesListResponseDTO;
import rocketseat.com.passin.dto.event.EventIdDTO;
import rocketseat.com.passin.dto.event.EventRequestDTO;
import rocketseat.com.passin.dto.event.EventResponseDTO;
import rocketseat.com.passin.service.AttendeeService;
import rocketseat.com.passin.service.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

	private final EventService EventService;
	private final AttendeeService attendeeService;
	
	@GetMapping("/{id}")
	public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
		EventResponseDTO event = this.EventService.getEventDetail(id);
		
		return ResponseEntity.ok(event);
	}
	
	
	@PostMapping
	public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
		EventIdDTO eventIdDTO = this.EventService.createEvent(body);
		
		var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
		
		return ResponseEntity.created(uri).body(eventIdDTO);
	}
	
	
	@PostMapping("/{eventId}/attendees")
	public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId,  @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
		AttendeeIdDTO attendeeIdDTO = this.EventService.registerAttendeeOnEvent(eventId, body);
		
		var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();
		
		return ResponseEntity.created(uri).body(attendeeIdDTO);
	}
	
	
	
	
	@GetMapping("/attendees/{id}")
	public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){
		AttendeesListResponseDTO attendeesListResponse = this.attendeeService.getEventsAttendee(id);
		
		return ResponseEntity.ok(attendeesListResponse);
	}
}
