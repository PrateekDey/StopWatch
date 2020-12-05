import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stopwatch extends JFrame {

	private JPanel contentPane;
	private JLabel lbhr;
	private JLabel lbmin;
	private JLabel lbsec;
	private JLabel lbms;
	private JButton start;
	private JButton stop;
	private JButton reset;
	private JButton lap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stopwatch frame = new Stopwatch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	static int hours = 0;
	static int minutes = 0;
	static int seconds = 0;
	static int milliseconds = 0;
	static int count = 1;
	
	static boolean state=true;
	private JComboBox<String> lapcounter;
	
	public Stopwatch() {
		setBackground(SystemColor.control);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel header = new JLabel("StopWatch");
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setFont(new Font("Georgia", Font.PLAIN, 20));
		header.setBounds(200, 8, 150, 30);
		panel.add(header);
		
		JLabel description = new JLabel("Simple lap Counter StopWatch (Can Count Upto 10 laps)");
		description.setHorizontalAlignment(SwingConstants.CENTER);
		description.setFont(new Font("Georgia", Font.ITALIC, 15));
		description.setBounds(20, 30, 540, 30);
		panel.add(description);
		
		lbhr = new JLabel("00");
		lbhr.setFont(new Font("Arial", Font.BOLD, 40));
		lbhr.setHorizontalAlignment(SwingConstants.CENTER);
		lbhr.setBounds(344, 70, 50, 50);
		panel.add(lbhr);
		
		JLabel label = new JLabel(":");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 40));
		label.setBounds(389, 70, 20, 50);
		panel.add(label);
		
		lbsec = new JLabel("00");
		lbsec.setHorizontalAlignment(SwingConstants.CENTER);
		lbsec.setFont(new Font("Arial", Font.BOLD, 40));
		lbsec.setBounds(464, 70, 50, 50);
		panel.add(lbsec);
		
		JLabel label_1 = new JLabel(":");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.BOLD, 40));
		label_1.setBounds(509, 70, 20, 50);
		panel.add(label_1);
		
		lbmin = new JLabel("00");
		lbmin.setHorizontalAlignment(SwingConstants.CENTER);
		lbmin.setFont(new Font("Arial", Font.BOLD, 40));
		lbmin.setBounds(404, 70, 50, 50);
		panel.add(lbmin);
		
		JLabel label_4 = new JLabel(":");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Arial", Font.BOLD, 40));
		label_4.setBounds(449, 70, 20, 50);
		panel.add(label_4);
		
		lbms = new JLabel("00");
		lbms.setFont(new Font("Arial", Font.BOLD, 20));
		lbms.setBounds(530, 87, 42, 30);
		panel.add(lbms);
		
		lapcounter = new JComboBox();
		lapcounter.setFont(new Font("Georgia", Font.PLAIN, 15));
		lapcounter.setMaximumRowCount(10);
		lapcounter.setBackground(Color.WHITE);
		lapcounter.setBounds(348, 153, 212, 30);
		panel.add(lapcounter);
		
		JLabel lblNewLabel_2 = new JLabel("Hr     :     min     :     sec     :      ms");
		lblNewLabel_2.setFont(new Font("Georgia", Font.ITALIC, 14));
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setBounds(360, 120, 200, 20);
		panel.add(lblNewLabel_2);
		
		start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				state = true;
				Thread t=new Thread() {
					public void run() {
						for(;;) {
							if(state == true) {
								try {
									sleep(1);
									if(milliseconds>1000) {
										milliseconds=0;
										seconds++;
									}
									if(seconds>60) {
										milliseconds=0;
										seconds=0;
										minutes++;
									}
									if(minutes>60) {
										milliseconds=0;
										seconds=0;
										minutes=0;
										hours++;
									}
									lbms.setText(Integer.toString(milliseconds));
									milliseconds++;
									lbsec.setText(Integer.toString(seconds));
									lbmin.setText(Integer.toString(minutes));
									lbhr.setText(Integer.toString(hours));
									
								}catch(Exception ec) {
									JOptionPane.showMessageDialog(null, "Error Occured");
								}
							}
							else {
								break;
							}
						}
					}
				};
				t.start();
			}
		});
		start.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
		start.setBounds(20, 70, 130, 35);
		panel.add(start);
		
		stop = new JButton("Stop");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = false;
			}
		});
		stop.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
		stop.setBounds(170, 70, 130, 35);
		panel.add(stop);
		
		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = false;
				hours=0;
				minutes=0;
				seconds=0;
				milliseconds=0;
				
				lbhr.setText("00");
				lbmin.setText("00");
				lbsec.setText("00");
				lbms.setText("00");
				
				lapcounter.removeAllItems();
				count = 1;
			}
		});
		reset.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
		reset.setBounds(20, 130, 130, 35);
		panel.add(reset);
		
		lap = new JButton("Lap");
		lap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = true;
				String display=(Integer.toString(count))+") "+lbhr.getText()+"hr: "+lbmin.getText()+"min: "+lbsec.getText()+"sec: "+lbms.getText()+"ms";
				if(lapcounter.getItemCount()==10) {
					JOptionPane.showMessageDialog(null, "Can't accomodate more than 10 lap counts");
				}
				else {
					lapcounter.addItem(display);
				}
				count++;
			}
		});
		lap.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 15));
		lap.setBounds(170, 130, 130, 35);
		panel.add(lap);
		
		JLabel lblThisAppletIs = new JLabel("This Applet is Created by Prateek Dey");
		lblThisAppletIs.setEnabled(false);
		lblThisAppletIs.setFont(new Font("Georgia", Font.ITALIC, 15));
		lblThisAppletIs.setBounds(30, 180, 270, 25);
		panel.add(lblThisAppletIs);
	}
}
