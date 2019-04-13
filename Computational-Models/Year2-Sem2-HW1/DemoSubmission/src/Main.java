import java.util.Arrays;
import java.util.List;

import ac.il.afeka.Submission.Submission;
import ac.il.afeka.fsm.DFSM;

public class Main implements Submission, Assignment1 {

	
	
	
	@Override
	public List<String> submittingStudentIds() {
		return Arrays.asList("319498580", "204409593", "313459869");
	}

	@Override
	public boolean compute(String dfsmEncoding, String input) throws Exception {
		DFSM dfsm = new DFSM(dfsmEncoding);
		return dfsm.compute(input);
	}

}
