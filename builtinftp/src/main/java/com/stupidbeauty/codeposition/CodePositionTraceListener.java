package com.stupidbeauty.codeposition;

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

public interface CodePositionTraceListener 
{
  public void onNewPosition(String positionString); //!< new psoition.

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
