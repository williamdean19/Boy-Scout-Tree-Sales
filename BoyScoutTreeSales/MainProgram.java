




// specify the package



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.TLC;
// project imports
import event.Event;
import userinterface.MainStageContainer;
import userinterface.WindowPosition;


//==============================================================
public class MainProgram extends Application
{

	private TLC myTLC;		// the main behavior for the application

	/** Main frame of the application */
	private Stage mainStage;


	// start method for this class, the main application object
	//----------------------------------------------------------
	@Override
	public void start(Stage primaryStage)
	{
	   System.out.println("ASGN2 Revision 2-20-16");
	   System.out.println("Copyright Team 6");

           // Create the top-level container (main frame) and add contents to it.
	   MainStageContainer.setStage(primaryStage, "Boy Scout Xmas Tree Sales System");
	   mainStage = MainStageContainer.getInstance();

	   // Finish setting up the stage (ENABLE THE GUI TO BE CLOSED USING THE TOP RIGHT
	   // 'X' IN THE WINDOW), and show it.
           mainStage.setOnCloseRequest(new EventHandler <javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent event) {
                System.exit(0);
            }
           });

           try
	   {
		myTLC = new TLC();
	   }
	   catch(Exception exc)
	   {
		System.err.println("Could Not TLC!");
		new Event(Event.getLeafLevelClassName(this), "Tester2.<init>", "Unable to create TLC object", Event.ERROR);
		exc.printStackTrace();
	   }


  	   WindowPosition.placeCenter(mainStage);

           mainStage.show();
	}


	/** 
	 * The "main" entry point for the application. Carries out actions to
	 * set up the application
	 */
	//----------------------------------------------------------
    	public static void main(String[] args)
	{

		launch(args);
	}

}
