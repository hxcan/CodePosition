package com.stupidbeauty.codeposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
// import com.stupidbeauty.carddesignclient.ChopPiece;
import java.net.BindException;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import java.util.Date;    
import java.time.format.DateTimeFormatter;
import java.io.File;
import com.koushikdutta.async.AsyncServerSocket;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.ListenCallback;
import org.apache.commons.io.FileUtils;
import com.koushikdutta.async.callback.ConnectCallback;
import java.net.InetSocketAddress;
import android.text.format.Formatter;
import android.net.wifi.WifiManager;
import java.util.Random;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CodePositionTraceManager 
{
  private String className=null; //!< Event listener.
  private String methodName=null; //!< Error listener. Chen xin. 
  private Context context; //!< 执行时使用的上下文。
  private static final String TAG="CodePositionTraceManager"; //!< The tag used for debug code.
  private InetAddress host;
  private int port;
  private boolean allowActiveMode=true; //!< 是否允许主动模式。
  private int lineNumber=0; //!< 根目录。
  private ArrayList<CodePositionTraceListener> traceListeners=new ArrayList<>(); //!< Trace listeners.
	private static CodePositionTraceManager mInstance = null; //!< The shared instance.
  
  /**
  * Get or crate the shared instance.
  */
	public static CodePositionTraceManager sharedInstance() 
	{
		if (mInstance == null)  // The instance does not exist
		{
			mInstance = new CodePositionTraceManager(); // Create the instance.
		} // if (mInstance == null)  // The instance does not exist
		
		return mInstance;
	} // public static HxLauncherApplication getInstance() 

	/**
  * Add trace
  */
  public void addTrace(CodePosition result)
  {
    for(CodePositionTraceListener curentListener: traceListeners) // Report one by one
    {
      curentListener.onNewPosition(result.toString()); // Report.
    } // for(CodePositionTraceListener curentListener: traceListeners) // Report one by one
  } // public void addTrace(CodePosition result)
  
  /**
  * Add listener.
  */
  public void addTraceListener(CodePositionTraceListener selfthis)
  {
    traceListeners.add(selfthis); // add into the list.
  } // public void addTraceListener(CodePositionTraceListener selfthis)
    
  public void setClassName(String eventListener)
  {
    this.className=eventListener;
  } //public void setEventListener(eventListener)
    
  public void setLineNumber(int root)
  {
    lineNumber=root;
  }
        
  public void setMethodName(String errorListener)    
  {
    this.methodName = errorListener;
  } //public void setErrorListener(ErrorListener errorListener)    
  
  public String toString()
  {
    String result;
    
    result= className + " " + methodName + " " + lineNumber;
    
    return result;
  } // public String toString()

  public static CodePosition newInstance()
  {
    CodePosition result=new CodePosition();
    
    StackTraceElement ste = Thread.currentThread().getStackTrace()[3];

    int lineNumber=ste.getLineNumber();
    String methodName=ste.getMethodName();
    String className=ste.getClassName();
    
    result.setClassName(className);
    result.setMethodName(methodName);
    result.setLineNumber(lineNumber);
    
    return result;
  } //public FtpServer(String host, int port, Context context, boolean allowActiveMode) 
}
