/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eexamination;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.RadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.Timer;


/**
 *
 * @author user
 */
public class ExaminationAttempt extends javax.swing.JFrame implements ActionListener {
    int count=0, current =0, x=1,y=1, now=0;
    int  m[]=new int [10];
    public Object[] jRadioButton;
    public String question="1";
    public String answer;
    public int Min=0;
    public int Sec=0;
    public int  Marks=0;
  
   
   Timer time;
   

    /**
     * Creates new form ExaminationAttempt
     */
   
     public void answerCheck(){
    String studentAnswer= "";
    if(jRadioButton1.isSelected())
    {
    studentAnswer=jRadioButton1.getText();
    }
    
    else if(jRadioButton2.isSelected())
    {
    studentAnswer=jRadioButton1.getText();
    }
    
    else if(jRadioButton3.isSelected())
    {
    studentAnswer=jRadioButton3.getText();
    }
    
    else
    {
    studentAnswer=jRadioButton4.getText();    
    }
    
    if(studentAnswer.equals(answer))
        {
    Marks=Marks+1;
    String Mark1=String.valueOf(Mark);
    Mark.setText(Mark1);
    }
    
    //question change
int questionId1=Integer.parseInt(question);
questionId1=questionId1+1;
question=String.valueOf(questionId1);

//hide next button
if(question.equals("10"))
{
    Next.setVisible(false);
}
     }
     public void question()
     {
             try {
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Java_final_ica","trevy" , "1234");
            Statement ps = conn.createStatement();     
            
            ResultSet rs=ps.executeQuery("Select * from QUESTIONS");
            while(rs.next());
            {
                {
                String Q = rs.getString(1);
                String jRadioButton1=  rs.getString(2);
                String jRadioButton2= rs.getString(3);
                String jRadioButton3= rs.getString(4);
                String jRadioButton4= rs.getString(5);
             
                Q.setText(Question);
                jRadioButton1.setText(Option1);
                jRadioButton2.setText(Option2);
                jRadioButton3.setText(Option3);
                jRadioButton4.setText(Option4);
                }
            }
            
             }
             catch (SQLException ex) {
                 Logger.getLogger(ExaminationAttempt.class.getName()).log(Level.SEVERE, null, ex);
             }
            
         }
      public void submit()
          
    {
 String name=jLabel8.getText();
    check();
    try{
   Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/Java_final_ica","trevy", "1234");
    Statement st=conn.createStatement();
    st.executeQuery("update REGISTRATION set Marks='"+marks+"' where Username='"+Username+"'");
    
    JOptionPane.showMessageDialog(null,marks);
    }
    
    catch(Exception e)
    {
          JOptionPane.showMessageDialog(null,e);
    }
    }
      public ExaminationAttempt(String message) {
        super(message);
        initComponents();
        
            Next=new JButton("Next");
            Bookmark=new JButton("Bookmark");
            Next.addActionListener((ActionListener) this);
            Bookmark.addActionListener((ActionListener) this);
            add(Next);
            add(Bookmark);
            set();
            try{
           setDefaultCloseoperation(ExaminatonAttempt.EXIT_ON_CLOSE);
           }
           catch(Exception ex)
                  {
                 System.out.println("bn"+ex);
                  }
            setLayout(null);
           setLocation(250,100);
           setVisible(true);
           setSize(600,300);
            setLocationRelativeTo(this);
    time=new Timer(1000, new ActionListener() {
    
    @Override
    public void actionPerformed(ActionEvent e){
    Secs.setText(String.valueOf(Secs));
            Mins.setText(String.valueOf(Mins));
    if(Secs==60)
    {
    Secs=0;
    Mins++;
    if(Mins==10)
    {
        time.stop();
    
    check();
    
    submit();
    
    
    }
    }
    Secs++;
    }
    });
    time.start();
}  
       
    

   public ExaminationAttempt() {
        initComponents();
        
            Next=new JButton("Next");
            Bookmark=new JButton("Bookmark");
            Next.addActionListener((ActionListener) this);
            Bookmark.addActionListener((ActionListener) this);
            add(Next);
            add(Bookmark);
            set();
        
   public void set(){
    try {
        Connection conn=DriverManager.getConnection("jdbc:derby://localhost:1527/Java_final_ica","trevy" , "1234");
        Statement ps = conn.createStatement();
        ResultSet rsp=ps.executeQuery("Select * from REGISTRATION");
        while(rsp.next())
            
            {
            jLabel8.setText(rsp.getString(1));
        }
            ResultSet rs=ps.executeQuery("Select * from Question_paper");
            while(rs.next())
        {
            jRadioButton3.setSelected(true);
            if (current==0){
            String Question= rs.getString(1);
            String Option1=  rs.getString(2);
            String Option2= rs.getString(3);
            String Option3= rs.getString(4);
            String Option4= rs.getString(5);
             
             Q.setText(Question);
             jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
             
             
               }     
                 if (current==1){
              String Question = rs.getString(1);
             String Option1   =  rs.getString(2);
             String Option2   =rs.getString(3);
           String Option3=  rs.getString(4);
            String Option4=  rs.getString(5);
                    jLabel2.setText(Question);
             jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
                 }   
                 if (current==2){
            String Question =  rs.getString(1);
             String Option1  =rs.getString(2);
           String Option2=  rs.getString(3);
           String Option3=  rs.getString(4);
           String Option4 = rs.getString(5);
                    jLabel2.setText(Question);
             jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
                    
                    } 
                    if (current==3){
             String Question=  rs.getString(1);
             String Option1=  rs.getString(2);
            String Option2= rs.getString(3);
           String Option3=  rs.getString(4);
           String Option4=  rs.getString(5);
                    jLabel2.setText(Question);
             jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
                    
                    }
                       if (current==4){
           String Question=    rs.getString(1);
           String Option1=    rs.getString(2);
            String Option2= rs.getString(3);
            String Option3= rs.getString(4);
            String Option4= rs.getString(5);
                    jLabel2.setText(Question);
             jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
                    
                    }
                          if (current==5){
            String Question=   rs.getString(1);
            String Option1=   rs.getString(2);
           String Option2=  rs.getString(3);
            String Option3= rs.getString(4);
            String Option4= rs.getString(5);
                    jLabel2.setText(Question);
             jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
             
                    
                    }
                             if (current==6){
           String Question=    rs.getString(1);
            String Option1=   rs.getString(2);
            String Option2= rs.getString(3);
           String Option3=  rs.getString(4);
           String Option4=  rs.getString(5);
           
                    jLabel2.setText(Question);
             jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
                    
                }
                             
            if (current==7){
            String Question=  rs.getString(1);
            String Option1=  rs.getString(2);
            String Option2= rs.getString(3);
            String Option3= rs.getString(4);
            String Option4= rs.getString(5);
             
             jLabel2.setText(Question);
             jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
                    
                }
            
                if (current==8){
            String Question=   rs.getString(1);
            String Option1= rs.getString(2);
            String Option2= rs.getString(3);
            String Option3= rs.getString(4);
            String Option4=rs.getString(5);
             
            jLabel2.setText(Question);
            jRadioButton1.setText(Option1);
            jRadioButton2.setText(Option2);
            jRadioButton3.setText(Option3);
            jRadioButton4.setText(Option4);
                    
                    }
                                      if (current==9){
             String Question=  rs.getString(1);
             String Option1 =  rs.getString(2);
             String Option2 =  rs.getString(3);
             String Option3 =  rs.getString(4);
             String Option4 =  rs.getString(5);
                    jLabel2.setText(Question);
                     jRadioButton1.setText(Option1);
             jRadioButton2.setText(Option2);
             jRadioButton3.setText(Option3);
             jRadioButton4.setText(Option4);
                    
                    }
                                      
                                 
        }
        
    }
    catch (SQLException ex) {
        Logger.getLogger(ExaminationAttempt.class.getName()).log(Level.SEVERE, null, ex);
    }
    } 
    boolean check()
           {
           if (current ==0)
               return(jRadioButton2.isSelected());
           if(current==1)
               return(jRadioButton1.isSelected());
           if (current==2)
               return(jRadioButton4.isSelected());  
           if (current==3)
               return(jRadioButton3.isSelected());
           if (current==4)
           return(jRadioButton1.isSelected());
           if (current==5)
           return(jRadioButton1.isSelected());
           if(current==6)
               return(jRadioButton2.isSelected());
           if(current==7)
               return(jRadioButton3.isSelected());
           if(current==8)
               return(jRadioButton1.isSelected());
           if(current==9)
               return(jRadioButton1.isSelected());
           return false;
           
    }
    @Override
            public void actionPerformed(ActionEvent ex){Bookmark.setText("Result");
           setVisible(true);
           
           for (int i = 0, y = 1; i < x; i++, y++ ){
           if(ex.getActionCommand().equals("Bookmark"+y))
           {
               if(check())
               count=count+1;
               now=current;
               current=m[y];
               set();
               ((JButton)ex.getSource()).setEnabled(false);
               current=now;
                   }
           
           }
           if(ex.getActionCommand().equals("Result")){
               if(check())
                   count=count+1;
               current++;
               JOptionPane.showMessageDialog(this,"correct answers"+count);
               System.exit(0);
           }
            }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();
        btnBookmark = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setText("Question Paper: ");

        jLabel4.setText("Date:");

        jLabel6.setText("Total time:");

        jLabel7.setText("10 Min");

        jLabel8.setText("Time Taken:");

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("00");

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("00");

        jLabel9.setText("Name:");

        jLabel13.setText("Total Marks");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)))
                        .addGap(7, 7, 7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnBookmark.setText("Bookmark");
        btnBookmark.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookmarkActionPerformed(evt);
            }
        });

        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                        .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnNext)
                            .addGap(18, 18, 18)
                            .addComponent(btnBookmark)
                            .addGap(26, 26, 26)
                            .addComponent(jButton1))
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton4)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnBookmark)
                    .addComponent(jButton1))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel3.setText("Answer the following Questions ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(245, 245, 245))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1455, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void BookmarkActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
         Bookmark.setText("Result");
         setVisible(true);
           
           for (int i = 0, y = 1; i < x; i++, y++ ){
           if(evt.getActionCommand().equals("Bookmark"+y))
           {
               if(check())
               count=count+1;
               now=current;
               current=m[y];
               set();
               ((JButton)evt.getSource()).setEnabled(false);
               current=now;
                   }
           
           }
           if(evt.getActionCommand().equals("Result")){
               if(check())
                   count=count+1;
               current++;
               JOptionPane.showMessageDialog(this,"correct answers"+count);
               System.exit(0);
           
          
           }
    }                                           

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        answerCheck();
       question();
        
        
    }                                       

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
          answerCheck();
       question();
        
        int a= JOptionPane.showConfirmDialog(null, "Do you really want to submit","Select",JOptionPane.YES_NO_OPTION);
        if(a==0)
        {
        answerCheck();
        check();
        
        }
    
    }                                        

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
      if(jRadioButton1.isSelected()){
          
      
      jRadioButton2.setSelected(false);
      jRadioButton3.setSelected(false);
      jRadioButton4.setSelected(false);
          
    }                                             
    }
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
      if(jRadioButton2.isSelected()){
      jRadioButton1.setSelected(false);
      jRadioButton3.setSelected(false);
      jRadioButton4.setSelected(false);
      }
      
      
          
    }                                             

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
  if(jRadioButton3.isSelected()){
          
      
      jRadioButton1.setSelected(false);
      jRadioButton2.setSelected(false);
      jRadioButton4.setSelected(false);
          
    }                                             
    }
    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        if(jRadioButton4.isSelected()){
          
      
      jRadioButton1.setSelected(false);
      jRadioButton3.setSelected(false);
      jRadioButton4.setSelected(false);
          
    }                                             
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Question_paper_Platform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Question_paper_Platform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Question_paper_Platform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Question_paper_Platform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {new ExaminationAttempt().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify                     
        private javax.swing.JButton Bookmark;
    private javax.swing.JButton Cancel;
    private javax.swing.JLabel Mark;
    private javax.swing.JLabel Mins;
    private javax.swing.JButton Next;
    private javax.swing.JLabel Q;
    private javax.swing.JLabel Secs;
    private javax.swing.JButton Submit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration 
    private void setDefaultCloseoperation(int EXIT_ON_CLOSE){
        }
}
      
    public ExaminationAttempt(String message)
{
        super(message);
        initComponents();
        
            Next=new JButton("Next");
            Bookmark=new JButton("Bookmark");
            Next.addActionListener((ActionListener) this);
            Bookmark.addActionListener((ActionListener) this);
            add(Next);
            add(Bookmark);
            set();
            
            

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     }
             
    

    public ExaminationAttempt(){
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Mins = new javax.swing.JLabel();
        Secs = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Q = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        Submit = new javax.swing.JButton();
        Bookmark = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Mark = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Question Paper");

        jLabel2.setText("Name");

        jLabel3.setText("Duration");

        jLabel4.setText("10Mins");

        jLabel5.setText("Time Taken");

        Mins.setText("00");

        Secs.setText("00");

        jLabel9.setText("Question");

        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        Bookmark.setText("Bookmark");

        Next.setText("Next");

        Cancel.setText("Cancel");

        jLabel11.setText("Marks");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Q, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Bookmark)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 5, Short.MAX_VALUE)))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(Mins)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Secs))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(Mark, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Mins)
                    .addComponent(Secs)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Mark, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Q, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Submit)
                    .addComponent(Bookmark)
                    .addComponent(Next)
                    .addComponent(Cancel))
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubmitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExaminationAttempt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExaminationAttempt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExaminationAttempt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExaminationAttempt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExaminationAttempt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bookmark;
    private javax.swing.JButton Cancel;
    private javax.swing.JLabel Mark;
    private javax.swing.JLabel Mins;
    private javax.swing.JButton Next;
    private javax.swing.JLabel Q;
    private javax.swing.JLabel Secs;
    private javax.swing.JButton Submit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void check() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void set() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
