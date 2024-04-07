package rocketseat.com.passin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import rocketseat.com.passin.dto.attendee.AttendeeBadgeResponseDTO;
import rocketseat.com.passin.service.AttendeeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendees")
public class AttendeeController {
	
	private final AttendeeService attendeeService;

	@GetMapping("/{attendeeId}/badge")
	public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
		AttendeeBadgeResponseDTO response =	this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
		
		
		return ResponseEntity.ok(response);
	}
	
}
