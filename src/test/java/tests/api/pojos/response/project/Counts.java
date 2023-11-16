package tests.api.pojos.response.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)public class Counts{
	private int cases;
	private int suites;
	private Defects defects;
	private int milestones;
	private Runs runs;
}