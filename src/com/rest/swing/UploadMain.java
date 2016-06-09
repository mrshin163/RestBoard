package com.rest.swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UploadMain extends JFrame implements ActionListener {
	URL url;
	HttpURLConnection con;

	JTextField t_title;
	JButton bt_find;
	JPanel p;
	Canvas can;
	JButton bt_regist;
	JFileChooser chooser;
	Image img; // ������ ������ ���Ϸκ��� �̹��� ����!!
	String boundary; // ���ε�� ���Ǵ� ��蹮�ڿ�...
	String CRLF = "\n\r";
	File selectFile;
	String mimeType;
	String charset="utf-8";

	public UploadMain() {
		t_title = new JTextField(15);
		bt_find = new JButton("����ã��");
		p = new JPanel();
		p.add(t_title);
		p.add(bt_find);

		setLayout(new FlowLayout());

		add(p);

		can = new Canvas() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		can.setPreferredSize(new Dimension(300, 300));
		can.setBackground(Color.YELLOW);
		add(can);

		add(bt_regist = new JButton("���ε�"));

		// �����ʿ��� ����
		bt_find.addActionListener(this);
		bt_regist.addActionListener(this);

		chooser = new JFileChooser("C:/Users/Public/Pictures/Sample Pictures");

		// �ٿ���� ����
		boundary = Long.toHexString(System.currentTimeMillis());
		System.out.println(boundary);

		setVisible(true);
		setSize(300, 420);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// ���ϴ� �̹��� ����
	public void find() {
		int result = chooser.showOpenDialog(this);

		if (result == JFileChooser.APPROVE_OPTION) {
			// �̹��� ������ �����صΰ�, �̸� ���� ����!!
			selectFile = chooser.getSelectedFile();

			// ����Ÿ�� ����
			/*			
			Path path = Paths.get(selectFile.getAbsolutePath());
			try {
				mimeType = Files.probeContentType(path);
				System.out.println(mimeType);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 */
			
			try {
				img = ImageIO.read(selectFile);
				can.repaint();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// �������� ���ε�
	public void upload() {
		try{
			   URL url = new URL("http://70.12.109.81:9090/device/upload");
			   con=(HttpURLConnection)url.openConnection();
			   
			   con.setDoOutput(true);
			   con.setDoInput(true);
			   con.setUseCaches(false);
			   con.setRequestMethod("POST");
			   con.setRequestProperty("Connection", "keep-alive");
			   con.setRequestProperty("Cache-Control", "max-age=0");
			   con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);  
			   
			   OutputStream output = con.getOutputStream();

			   con.connect();
			   
			   PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
			      // Send normal param.
			      writer.append("--" + boundary).append(CRLF);
			      writer.append("Content-Disposition: form-data; name=\"title\"").append(CRLF);
			      writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
			      writer.append(CRLF).append("zino").append(CRLF).flush();

			      // Send binary file.
			      
			      writer.append("--" + boundary).append(CRLF);
			      writer.append("Content-Disposition: form-data; name=\"myFile\"; filename=\"" + selectFile.getName() + "\"").append(CRLF);
			      writer.append("Content-Type: " + HttpURLConnection.guessContentTypeFromName(selectFile.getName())).append(CRLF);
			      writer.append("Content-Transfer-Encoding: binary").append(CRLF);
			      writer.append(CRLF).flush();
			      
			      Files.copy(selectFile.toPath(), output);
			      output.flush(); // Important before continuing with writer!
			      writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
			      			     
			      // End of multipart/form-data.
			      writer.append("--" + boundary + "--").append(CRLF).flush();
			      writer.close();
			      
			      
			      int code=0;
			      code=con.getResponseCode();
			      
			      System.out.println(code); 
			      
			      
			  }catch(IOException e){
			   e.printStackTrace();
			  } 
			
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == bt_find) {
			find();
		} else if (obj == bt_regist) {
			upload();
		}
	}

	public static void main(String[] args) {
		new UploadMain();
	}

}
