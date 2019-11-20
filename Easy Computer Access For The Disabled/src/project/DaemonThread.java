package project;

/* For this class to work do the following.
 * 1. In the "resource" folder there will be "opencvlib" folder.
 * 2. In "libraries" folder there will be a many jar file.
 * 3. Configure JRE System Library and add those jar file to the JRE System library.
*/

// For this to work, we have used Haar Cascade classifier to detect eye blink, if we want to control the software with our eye blink.
// It will take photos of our face in 20 - 30 FPS. (Don't know the exact number)
// It will use Haar Classifier to check for face and then eyes.
// In a frame if we don't detect and eye and then in the next frame if the eye comes back we will consider that as a blink.
// And we will call the Switch access function to perform the appropriate action.
// Here we are implementing switch access scanning using eye blink.
// We have not implemented step scanning using eye blink.
// NOTE: This feature is not in perfect working condition. It has some bug. 
// We implemented it as a concept to show something like it can be done.

// In the "resource" folder, there is a "opencvdll" folder having "opencv_java341.dll" file.
// You may want to add it to system path or configure it in some way so that eye blink detection work properly.
// This is where you might get some error, if the file is not loaded properly. 

// I have not cited any reference for this in the project report. It was a mistake in our part as we were in a hurry to compete the project.
// So here are a few reference.
// Face detection code - https://github.com/tahaemara/real-time-face-detection-using-opencv-with-java/blob/master/src/gui/FaceDetection.java
// Open CV Cascade classifier - https://docs.opencv.org/3.4/db/d28/tutorial_cascade_classifier.html

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class DaemonThread implements Runnable 
{
	protected volatile boolean runnable = false;
	static DaemonThread myThread = null;
	public static int count_blink = 0;
	public static int counter=0;
	public static VideoCapture webSource = null;
	public static Mat frame = new Mat();
	public static MatOfByte mem = new MatOfByte();
	public static CascadeClassifier faceDetector = new CascadeClassifier(DaemonThread.class.getResource("haarcascade_frontalface_default.xml").getPath().substring(1).replaceAll("%20", " "));
	public static CascadeClassifier cascadeEyeClassifier = new CascadeClassifier(DaemonThread.class.getResource("haarcascade_eye.xml").getPath().substring(1).replaceAll("%20"," "));
	public static MatOfRect faceDetections = new MatOfRect();
	public static Rect rectCrop = null;
	public static boolean camerastarted=false;
	@Override
	public void run() 
	{
		synchronized (this)
		{
			while (runnable) 
			{
				if (webSource.grab()) 
				{
					try 
					{
						webSource.retrieve(frame);
						Graphics g = MainWindow.canvas.getGraphics();
						//System.out.println(DaemonThread.class.getResource("haarcascade_frontalface_default.xml").getPath().substring(1).replaceAll("%20"," "));
						faceDetector.detectMultiScale(frame, faceDetections);
						
						for (Rect rect : faceDetections.toArray())
						{
								Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 255,0));
								//rectCrop =new Rect(rect.x - 5,rect.y - 5,rect.width + 5,rect.height + 5);
						}
						MatOfRect eyes = new MatOfRect();
						cascadeEyeClassifier.detectMultiScale(frame, eyes);
						if(eyes.toArray().length==0)
						{
							count_blink++;
							//counter++;
							if(count_blink>=1)
							{
								//System.out.println("1. Blink Detected.");
								if(SwitchAcessScanning.row==-1)
								{
									System.out.println("2. Blink Detected.");
									Robot r=new Robot();
									r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									r.delay(25);
									r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
									//counter=0;
								}
								else
								{
									System.out.println("3. Blink Detected.");
									Robot r=new Robot();
									r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
									r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
								}
							}
							count_blink=0;
		
						}
						for (Rect rect : eyes.toArray())
						{
							Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(200, 200, 100),2);
						}
						//Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
						//Mat croppedImage=new Mat(frame,rectCrop);
						Imgcodecs.imencode(".bmp", frame, mem);
						Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
						BufferedImage buff = (BufferedImage) im;
						if (g.drawImage(buff, 0, 0, MainWindow.canvas.getWidth(), MainWindow.canvas.getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null)) 
						{
							if (runnable == false) 
							{
								System.out.println("Paused ..... ");
								this.wait();
							}
						}
					} 
					catch (Exception ex) 
					{
						System.out.println("Error!!");
						ex.printStackTrace();
					}
				}
			}
		}
	}
	public static void startCamera()
	{
		webSource = new VideoCapture(0); // video capture from default cam
		myThread = new DaemonThread(); //create object of threat class
		Thread t = new Thread(myThread);
		t.setDaemon(true);
		myThread.runnable = true;
		camerastarted=true;
		t.start();    
	}
	public static void stopCamera()
	{
		myThread.runnable = false;            // stop thread
		camerastarted=false;
		webSource.release();  // stop caturing fron cam
	}
}
