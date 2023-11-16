package tests.api.pojos.request.test_case;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCaseRequest{
	private int severity;
	private int isFlaky;
	private String description;
	private String title;
	private int priority;
	private int type;
	private int layer;
	private String postconditions;
	private int automation;
	private String preconditions;
	private int behavior;
	@JsonProperty("suite_id")
	private int suiteId;
	private int status;
}