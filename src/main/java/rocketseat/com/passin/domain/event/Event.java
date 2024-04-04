package rocketseat.com.passin.domain.event;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="events")
@Data
public class Event {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String details;
	
	@Column(nullable = false, name = "maximum_attendeers" )
	private String maximumAttendees;
	
	@Column(nullable = false, unique = true)
	private Integer slug;
	
}
