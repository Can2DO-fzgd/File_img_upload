package com.example.try_upload_pic_httpclient;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;   
import org.apache.http.HttpResponse;   
import org.apache.http.HttpStatus;   
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;   
import org.apache.http.client.HttpClient;   
import org.apache.http.client.methods.HttpPost;   
import org.apache.http.entity.mime.MultipartEntity;   
import org.apache.http.entity.mime.content.FileBody;   
import org.apache.http.entity.mime.content.StringBody;     
import org.apache.http.impl.client.DefaultHttpClient;   
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
	  // 要上传的文件路径，理论上可以传输任何文件，实际使用时根据需要处理
	  private String srcPath = Environment.getExternalStorageDirectory().getPath()+"/Desert.jpg";
	  private String srcPath2 = Environment.getExternalStorageDirectory().getPath()+"/Desert.apk";
	  // 服务器上接收文件的处理页面，这里根据需要换成自己的
	  private String actionUrl = "http://192.168.0.108:8014/upload/index_img.php";
	  private TextView mText1;
	  private TextView mText2;
	  private Button mButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    mText1 = (TextView) findViewById(R.id.myText2);
	    mText1.setText("Desert文件路径：\n" + srcPath);
	    mText2 = (TextView) findViewById(R.id.myText3);
	    mText2.setText("上传网址：\n" + actionUrl);
	    mButton = (Button) findViewById(R.id.myButton);
	    
	    mButton.setOnClickListener(new View.OnClickListener()
	    {
	      @Override
	      public void onClick(View v)
	      {
	        try {
				uploadFile(actionUrl);
			} catch (Exception e) {
				e.printStackTrace();
				setTitle(e.getMessage());
			}
	      }
	    });
	}
	
	
	  /* 上传文件至Server，uploadUrl：接收文件的处理页面 */
	  private void uploadFile(String uploadUrl) throws Exception
	  {
			HttpClient httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			HttpPost httppost = new HttpPost(uploadUrl);
			
			MultipartEntity entity = new MultipartEntity();
			
			//在表单中，增加第一个input type='file'元素，name=‘uploadedfile’
			File file = new File(srcPath);
			FileBody fileBody = new FileBody(file);
			entity.addPart("uploadedfile", fileBody);
			
			//在表单中，增加第一个input type='file'元素，name=‘anotherfile’
			File file2 = new File(srcPath2);
			FileBody fileBody2 = new FileBody(file2);
			entity.addPart("anotherfile", fileBody2);
			
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				Log.v("msg", EntityUtils.toString(resEntity));
			}
			
			httpclient.getConnectionManager().shutdown();

	  }
}