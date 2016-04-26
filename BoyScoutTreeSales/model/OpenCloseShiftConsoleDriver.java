package model;

import java.util.Properties;
import java.util.Vector;

public class OpenCloseShiftConsoleDriver {

	public static void main(String[] args)
	{
		//BEGIN OPEN/CLOSE SHIFT
		
		//----------------------------------------------------------------------
		// OPEN SHIFT USAGE:
		// In the actual code we will want to make sure there are no current open shifts
		// If there are open shifts we need to prompt user to end the current open shift
		// Before starting a new one
		//----------------------------------------------------------------------
		Properties openSessionProps = new Properties();

		openSessionProps.setProperty("StartDate", "4/23/2016");
		openSessionProps.setProperty("StartTime", "12:00");
		openSessionProps.setProperty("EndTime", "");
		openSessionProps.setProperty("StartingCash", "200.00");
		openSessionProps.setProperty("EndingCash", "");
		openSessionProps.setProperty("TotalCheckTransactionAmount", "");
		openSessionProps.setProperty("Notes", "");

		Session session = new Session(openSessionProps);
		
		
		SessionCollection sessionCollection = new SessionCollection();
		sessionCollection.findOpenSessions();
		
		if(sessionCollection.getSessionCollectionVector().size() == 0)
		{
			System.out.println("The number of open sessions is: " + sessionCollection.getSessionCollectionVector().size());
			session.save();
			System.out.println("The session ID is: " + session.getSessionId());
		}
		else
		{
			System.out.println("The number of open sessions is: " + sessionCollection.getSessionCollectionVector().size());
			System.out.println("There is an open session, please close the current session before beginning a new one.");
		}

		// I think we should just feed the scout Id directly to the input
		// (instead of populating a combobox of every scout)
		// Most jobs I've had I've had an "employee ID" that I had to memorize to punch in
		// This would be most similar to that

		// We make a new shift for every scout that is working
		Properties shiftProps = new Properties();

		shiftProps.setProperty("SessionID", session.getSessionId());
		shiftProps.setProperty("ScoutID", "1");
		shiftProps.setProperty("CompanionName", "Paul Dean");
		shiftProps.setProperty("StartTime", session.getStartTime());
		shiftProps.setProperty("EndTime", " ");
		shiftProps.setProperty("CompanionHours", "");

		Shift sh1 = new Shift(shiftProps);

		sh1.save();


		//----------------------------------------------------------------------------
		// CLOSE SHIFT USAGE
		// Set the session and shift properties to the appropriate values
		// And save (update) the session and shift to the database
		// This will close the session and allow a new session to be opened
		//----------------------------------------------------------------------------
		
		// Close the session
		session.setEndTime("16:00");
		session.setEndingCash("655.00");
		session.setNotes("Timmy broke a tree");
		session.setTotalCheckTransactionAmount("50.00");
		session.save();
		
		// Then we can close the open shifts
		ShiftCollection shiftCollection = new ShiftCollection();
		shiftCollection.findOpenShifts();
		System.out.println("The number of open shifts is: " + shiftCollection.getShiftCollectionVector().size() );

		for(int i = 0; i < shiftCollection.getShiftCollectionVector().size(); i++)
		{
			shiftCollection.getShiftCollectionVector().get(i).setEndTime(session.getEndTime());
			shiftCollection.getShiftCollectionVector().get(i).setCompanionHours("4"); // This will need to be computed but I'm doing it lazily for now
			shiftCollection.getShiftCollectionVector().get(i).save();
		}
		
		ShiftCollection shiftCollection2 = new ShiftCollection();
		shiftCollection.findOpenShifts();
		System.out.println("The number of open shifts is: " + shiftCollection2.getShiftCollectionVector().size() );
		
		

		// END OPEN/CLOSE SHIFT



	}

}
