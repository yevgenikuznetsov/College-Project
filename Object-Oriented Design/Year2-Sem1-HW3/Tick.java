
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Platform;
public class Tick implements Runnable
{	private int sleepTime = 1000;
	private ClockPane clockPane;
	private boolean suspended = false;
	private boolean resume = false;
	private AnnounceTimeOnSeparateThread announce = new AnnounceTimeOnSeparateThread();
	private  Lock lock = new ReentrantLock();
	private  Condition stop = lock.newCondition();
	
	public Tick(ClockPane clockPane)
	{ this.clockPane = clockPane;
	}
	@Override
	public void run()
	{    moveClock();
	}
	public  void moveClock()
	{  while (true)
	   { 	lock.lock();
	        if (suspended)
		        try
	            {	stop.await();
		        } 
	            catch (InterruptedException e)
	            { e.printStackTrace();
		        }
	            finally
	             {  lock.unlock();
	             }
	         else
	         {   resume = true;
	        	 clockPane.setCurrentTime();
	        	 if(clockPane.getSecond() == 0) {
	        		 announce.setHour(clockPane.getHour());
	        		 announce.setMinute(clockPane.getMinute());
	        		 new Thread(announce).start();
	        		 
	        	 }
  	             Platform.runLater(() -> clockPane.paintClock());
	             try
	             {	Thread.sleep(sleepTime);
		         } 
	             catch (InterruptedException e)
	             { 	e.printStackTrace();
		         }
	             finally
	             {  lock.unlock();
	             }
	        } 
	   }     
	}
	public  synchronized void pause()
	{   suspended = true;
	    resume = false; 
	}
	public void play()
	{  if (resume) return;
	   lock.lock();
	   suspended = false;
	   stop.signal();
	   lock.unlock();
	}
}
