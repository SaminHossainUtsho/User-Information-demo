 package user;
import java.awt.Color;import java.awt.Container;import java.awt.Font;import java.awt.event.ActionEvent;import java.io.BufferedWriter;import java.io.File;import java.io.FileNotFoundException;import java.io.FileWriter;import java.io.IOException;import java.util.Scanner;import javax.swing.JButton;import javax.swing.JComboBox;import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JOptionPane;import javax.swing.JPasswordField;import javax.swing.JTextArea;import javax.swing.JTextField;
 public class User extends JFrame
{
    JTextField name,user_phone,address;
    JTextArea view_info;
    JComboBox destination;
    JPasswordField pass;
    JButton click,view,submit;
    JLabel enter_phone,enter_pass,jp,jd,ja;
    String mobile,pin;
    Font font = new Font("Digital-7",Font.PLAIN,20);
    User()
    {
        Container con = this.getContentPane();
        con.setLayout(null);
        con.setBackground(Color.GRAY);    //  Till now we create a frame and add a container in that frame                        
        enter_phone = new JLabel("Name        : ");
        enter_phone.setBounds(30,50,170,40);
        enter_phone.setForeground(Color.white);
        enter_phone.setFont(font);       
        con.add( enter_phone); 
        name = new JTextField();
        name.setBounds(150,50,190,40);
        name.setForeground(Color.CYAN);
        name.setBackground(Color.BLACK);
        name.setFont(font);       
        con.add( name);        
        enter_pass = new JLabel("Password  : ");
        enter_pass.setBounds(30,110,170,40);
        enter_pass.setForeground(Color.white);
        enter_pass.setFont(font);       
        con.add( enter_pass);
        pass = new JPasswordField();
        pass.setBounds(150,110,190,40);
        pass.setForeground(Color.CYAN);
        pass.setBackground(Color.BLACK);
        pass.setFont(font);       
        con.add(pass);  
        click = new JButton();
        click.setBounds(160,250,110,40);
        click.setFont(font);
        con.add(click);       
    }
       private void take_user_information()             // 2nd frame will create from here.......
        {
            dispose();                  // To dispose old frame .......
            JFrame ob = new JFrame();
            ob.setVisible(true);
            ob.setBounds(460,25,440,655);
            ob.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ob.setResizable(false);
            Container con = ob.getContentPane();
            con.setLayout(null);
            con.setBackground(Color.GRAY);
             jp = new JLabel("Phone No   : ");
             jp.setBounds(20,30,150,40);
             jp.setForeground(Color.white);
             jp.setFont(font);       
             con.add( jp);
             jd= new JLabel("Destination  : ");
             jd.setBounds(20,105,150,40);
             jd.setForeground(Color.white);
             jd.setFont(font);       
             con.add( jd);
             ja = new JLabel("Address      : ");
             ja.setBounds(20,180,150,40 );
             ja.setForeground(Color.white);
             ja.setFont(font);       
             con.add( ja);
             user_phone = new JTextField();
             user_phone.setBounds(150,30,190,40);
             user_phone.setForeground(Color.CYAN);
             user_phone.setBackground(Color.BLACK);
             user_phone.setFont(font);       
             con.add( user_phone);
             String des[ ] = {"Dhaka","Chittagong","Sylhet","Rajshahi","Khulna","Coxs Bazar"};
             destination = new  JComboBox(des);
             destination.setBounds(150,100,190,40);
             destination.setFont(font);
             destination.setEditable(true);
             con.add( destination);
             address = new JTextField();
             address.setBounds(150,170,190,60);
             address.setForeground(Color.CYAN);
             address.setBackground(Color.BLACK);
             address.setFont(font);       
             con.add( address);
             view_info = new JTextArea();
             view_info.setBounds(10,250,410,200);  
             view_info.setBackground(Color.BLACK);
             view_info.setFont(font);   
             view_info.setEnabled(false);
             con.add( view_info);
             submit = new JButton("Submit");
             submit.setBounds(30,530,100,30);
             submit.setFont(font);
             con.add(submit);
             view = new JButton("View");
             view.setBounds(300,530,100,30);
             view.setFont(font);
             con.add(view);
             submit.addActionListener((ActionEvent ae) -> { 
                           if(user_phone.getText().isEmpty() || address.getText().isEmpty())
                           {
                               JOptionPane.showMessageDialog(null,"Enter Data Properly"); 
                           }
                           else
                           {
                               JOptionPane.showMessageDialog(null,"Data Submitted Successfuly");  
                        try
                           {
                                    FileWriter fw  = new FileWriter("user_info.txt",false);
                                    try (BufferedWriter bw = new BufferedWriter(fw))
                                        {
                                                bw.write(user_phone.getText()+"  "+destination.getSelectedItem().toString().replace(' ','-')+"  "+address.getText().replace(' ', ',')+"\n");
                                        } 
                                 } catch (IOException ex){ } 
                           }                          
             });
             
             view.addActionListener((ActionEvent ae) -> {
                
                 
                         String nam = null,phn = null,dest = null,add = null;
                         File file_read = new File("user_info.txt");                        
                         try {
                                     File old_read = new File("password_entry.txt");
                                    Scanner old_one = new Scanner(old_read);
                                    nam = old_one.next().replace('-',' ');
                                    Scanner read = new Scanner(file_read); 
                                    while(read.hasNext())
                                    {
                                        phn = read.next();
                                        dest = read.next();
                                        add = read.next();
                                    }
                                    view_info.setText("Name\t:  "+nam+"\n"+"Phone No       :  "+phn+"\n"+"Destination     :  "+dest+"\n"+"Address         :  "+add+"\n");               
                             } catch (FileNotFoundException ex){ } 
             });
 }                   
    public static void main(String[] args) throws FileNotFoundException  
    {
      User  ob = new  User();
      ob.setVisible(true);
      ob.setBounds(460,80,440,450);
      ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ob.setResizable(false);
      File file = new File("password_entry.txt");
      if(file.length() == 0)
       {
           ob.click.setText("SIGN IN");
           ob.click.addActionListener((ActionEvent ae1) -> { 
               if(ob.name.getText().isEmpty() || ob.pass.getText().isEmpty())
               {
                   JOptionPane.showMessageDialog(null,"Enter Data Properly"); 
               }
               else
               {
                   try {
               FileWriter fw  = new FileWriter("password_entry.txt",false);
               try (BufferedWriter bw = new BufferedWriter(fw)) {
                    bw.write(ob.name.getText().replace(' ','-')+" "+ob.pass.getText());                 
                    }
                } catch (IOException ex) {  }
                JOptionPane.showMessageDialog(null,"Successfully Signed In"); 
                ob.take_user_information();       // you have to go 2nd step from here...........
               }            
       });
       }
     else
      {
          ob.click.setText("LOG IN");
          Scanner read_file = new Scanner(file);
          ob.click.addActionListener((ActionEvent ae1) -> { 
               while(read_file.hasNext())
               {
                   ob.mobile = read_file.next().replace('-',' ');
                   ob.pin = read_file.next();
               }
               if(ob.name.getText().compareTo(ob.mobile) == 0 && ob.pass.getText().compareTo(ob.pin) == 0)
               {            
                   ob.take_user_information();      // you have to go 2nd step from here...........
               }
               else 
                   JOptionPane.showMessageDialog(null,"Log In Failed");
           });      
      } 
    }  
}