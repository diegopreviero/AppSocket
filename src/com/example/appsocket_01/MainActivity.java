package com.example.appsocket_01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	//fff
	EditText textoSaida;
	TextView textoEntrada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textoSaida = (EditText) findViewById(R.id.textosaida);
		textoEntrada = (TextView) findViewById(R.id.textoentrada);
		Button buttonEnviar = (Button)findViewById(R.id.enviar);

		buttonEnviar.setOnClickListener(buttonSendOnClickListener);

	}

	Button.OnClickListener buttonSendOnClickListener = new Button.OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Socket socket = null;
			DataOutputStream dataOutputStream = null;
			DataInputStream dataInputStream = null;

			try {
				socket = new Socket("192.168.15.103", 8081);
				
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
				dataInputStream = new DataInputStream(socket.getInputStream());
				
				dataOutputStream.writeUTF(textoSaida.getText().toString());
				textoEntrada.setText(dataInputStream.readUTF());
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if (socket != null){
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (dataOutputStream != null){
					try {
						dataOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (dataInputStream != null){
					try {
						dataInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}};
}