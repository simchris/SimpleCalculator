import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Simple Calculator
 * 
 * @author Simos Christodoulou
 * @version 1.3 29/11/2014
 */

// TODO:
// - Add Math.pow & Math.sqrt operations
// - Stabilize division results

public class Calculator extends JFrame
{
   private JPanel panel, displayPanel, keysPanel;
   private JTextField field;
   private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot, bplus, bminus,
         bdiv, bmulti, bequal, bclear;

   private char mathSign, lastSign; // It stores the math symbol that is
                                    // selected each time

   private String str = ""; // It stores the numbers typed by the user and
                            // displays them as a string in the text field

   private double number1, number2, result, tempResult; // To hold the
                                                            // numbers for the
                                                            // calculations
   private DecimalFormat frm = new DecimalFormat("#0.##");
   private boolean tempResultActive, zeroDivision;

   public Calculator()
   {
      // Call the JFrame super constructor and set the window name
      super("");

      // Initialize these booleans when the constructor creates the object
      tempResultActive = false;
      zeroDivision = false;

      // The display panel contains the text field and the clear button
      displayPanel = new JPanel();

      // The text field will display "0" by default
      field = new JTextField("0", 14);

      // Set the text font and size inside the text field
      Font font1 = new Font("SansSerif", 0, 14);
      field.setFont(font1);
      field.setBackground(Color.white);

      // so that the text will be displayed from the right of the text field
      field.setHorizontalAlignment(JTextField.RIGHT);

      // so that the field content can not be edited by keyboard inputs
      field.setEditable(false);
      bclear = new JButton("C");

      // Add the text field and the clear button to the display panel
      displayPanel.setLayout(new BorderLayout());
      displayPanel.setPreferredSize(new Dimension(200, 25));
      displayPanel.add(field, BorderLayout.WEST);
      displayPanel.add(bclear, BorderLayout.CENTER);

      // The keys panel contains all the other buttons
      keysPanel = new JPanel();
      keysPanel.setLayout(new GridLayout(4, 4));
      keysPanel.setBackground(Color.darkGray);

      b1 = new JButton("1");
      // b1.setBackground(Color.yellow);
      b2 = new JButton("2");
      b3 = new JButton("3");
      b4 = new JButton("4");
      b5 = new JButton("5");
      b6 = new JButton("6");
      b7 = new JButton("7");
      b8 = new JButton("8");
      b9 = new JButton("9");
      b0 = new JButton("0");
      bdot = new JButton(".");
      bplus = new JButton("+");
      bminus = new JButton("-");
      bdiv = new JButton("/");
      bmulti = new JButton("*");
      bequal = new JButton("=");

      // Add action listeners to all the buttons
      b0.addActionListener(new ListenToButtons());
      b1.addActionListener(new ListenToButtons());
      b2.addActionListener(new ListenToButtons());
      b3.addActionListener(new ListenToButtons());
      b4.addActionListener(new ListenToButtons());
      b5.addActionListener(new ListenToButtons());
      b6.addActionListener(new ListenToButtons());
      b7.addActionListener(new ListenToButtons());
      b8.addActionListener(new ListenToButtons());
      b9.addActionListener(new ListenToButtons());
      bdot.addActionListener(new ListenToDot());
      bplus.addActionListener(new ListenToSymbol());
      bminus.addActionListener(new ListenToSymbol());
      bdiv.addActionListener(new ListenToSymbol());
      bmulti.addActionListener(new ListenToSymbol());
      bequal.addActionListener(new ListenToEquals());
      bclear.addActionListener(new ListenToClear());

      // Add the buttons to the keys panel
      keysPanel.add(b7);
      keysPanel.add(b8);
      keysPanel.add(b9);
      keysPanel.add(bdiv);
      keysPanel.add(b4);
      keysPanel.add(b5);
      keysPanel.add(b6);
      keysPanel.add(bmulti);
      keysPanel.add(b1);
      keysPanel.add(b2);
      keysPanel.add(b3);
      keysPanel.add(bminus);
      keysPanel.add(b0);
      keysPanel.add(bdot);
      keysPanel.add(bequal);
      keysPanel.add(bplus);

      // Add the two component panels to the main panel
      panel = new JPanel();
      panel.setLayout(new BorderLayout());
      panel.add(displayPanel, BorderLayout.NORTH);
      panel.add(keysPanel, BorderLayout.CENTER);

      add(panel); // Add the main panel to the JFrame

      // JFrame settings
      setSize(230, 290);
      setResizable(true);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
   }

   // Action listener for the 'clear' button
   private class ListenToClear implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // When the clear button is pressed, the content of the text field is
         // set to the default "0" and the displayed string is also set to empty
         field.setText("0");
         str = "";
         mathSign = ' ';
         lastSign = ' ';
         zeroDivision = false;
         tempResultActive = false;
      }
   }

   // Action listener for all the number buttons
   private class ListenToButtons implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (!zeroDivision)
         {
            if (e.getSource() == b0)
            {
               if (field.getText().charAt(0) != '0' || str.length() > 1)
               {
                  str += "0";
                  field.setText(str);
               }
            }
            else if (e.getSource() == b1)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "1");
            }
            else if (e.getSource() == b2)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "2");
            }
            else if (e.getSource() == b3)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "3");
            }
            else if (e.getSource() == b4)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "4");
            }
            else if (e.getSource() == b5)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "5");
            }
            else if (e.getSource() == b6)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "6");
            }
            else if (e.getSource() == b7)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "7");
            }
            else if (e.getSource() == b8)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "8");
            }
            else if (e.getSource() == b9)
            {
               if (field.getText().charAt(0) == '0'
                     && field.getText().length() == 1)
               {
                  str = "";
               }
               field.setText(str += "9");
            }
         }
      }
   }

   // Action listener for the 'dot' button
   private class ListenToDot implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (!zeroDivision)
         {
            /*
             * First we must check if there is already a dot in the string being
             * displayed in the text field
             */
            boolean hasDot = false; // We assume that there isn't a dot

            // Run through the displayed string and check if there is a dot
            // character
            for (int i = 0; i < str.length(); i++)
            {
               if (str.charAt(i) == '.')
               {
                  hasDot = true;
                  break;
               }
            }

            // A dot is added to the displayed string, only if there isn't
            // already
            // one in it
            if (!hasDot)
            {
               // If the string is the default "0", then we add the dot after it
               if (field.getText().equals("0") && !str.equals("0")
                     || str.equals(""))
               {
                  field.setText(str += "0.");
               }
               else
               {
                  field.setText(str += ".");
               }
            }
         }
      }
   }

   // Action listener for the math symbols buttons
   private class ListenToSymbol implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (!zeroDivision)
         {
            if (e.getSource() == bplus)
            {
               if (tempResultActive && !str.equals(""))
               {
                  tempResult(); // Shows the temporary result
                  System.out.println("tempRsult  + activated");
               }

               if (!zeroDivision)
               {
                  number1 = Double.parseDouble(field.getText());
                  mathSign = '+';
                  lastSign = '+'; // We need that to call the repeatEquals()
                                  // method
                                  // later on
                  str = ""; // We empty the string in order to enter the
                            // second
                            // number

                  tempResultActive = true; // Enables the tempResult method
               }
            }
            else if (e.getSource() == bminus)
            {
               if (tempResultActive && !str.equals(""))
               {
                  tempResult();
                  System.out.println("tempRsult  - activated");
               }

               if (!zeroDivision)
               {
                  number1 = Double.parseDouble(field.getText());
                  mathSign = '-';
                  lastSign = '-';
                  str = "";
                  tempResultActive = true;
               }
            }
            else if (e.getSource() == bdiv)
            {
               if (tempResultActive && !str.equals(""))
               {
                  tempResult();
                  System.out.println("tempRsult  / activated");
               }

               if (!zeroDivision)
               {
                  number1 = Double.parseDouble(field.getText());
                  mathSign = '/';
                  lastSign = '/';
                  str = "";
                  tempResultActive = true;
               }
            }
            else if (e.getSource() == bmulti)
            {
               if (tempResultActive && !str.equals(""))
               {
                  tempResult();
                  System.out.println("tempRsult  * activated");
               }

               if (!zeroDivision)
               {
                  number1 = Double.parseDouble(field.getText());
                  mathSign = '*';
                  lastSign = '*';
                  str = "";
                  tempResultActive = true;
               }
            }
         }
      }
   }

   // Action listener for the 'equals' button
   private class ListenToEquals implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // The action is performed only when we don't have a division with zero
         if (!zeroDivision)
         {
            //
            // The first part is to get the second number and perform the
            // calculation
            //
            if (mathSign == '+') // if(mathSign == '*' && !str.equals("")) that
                                 // would not allow to perform a calculation
                                 // unless the user types a second number
            {
               number2 = Double.parseDouble(field.getText());
               result = number1 + number2;
               mathSign = '=';
            }
            else if (mathSign == '-')
            {
               number2 = Double.parseDouble(field.getText());
               result = number1 - number2;
               mathSign = '=';
            }
            else if (mathSign == '/')
            {
               number2 = Double.parseDouble(field.getText());

               if (number2 == 0)
               {
                  zeroDivision = true;
               }
               else
               {
                  result = number1 / number2;
                  mathSign = '=';
               }
            }
            else if (mathSign == '*')
            {
               number2 = Double.parseDouble(field.getText());
               result = number1 * number2;
               mathSign = '=';
            }
            else if (mathSign == '=')
            {
               // Repeats the last calculation if '=' is pressed more than once
               result = repeatEquals();
            }
            else
            {
               // Displays the number on the screen by default, when no math
               // operator is selected
               result = Double.parseDouble(field.getText());
            }

            //
            // The second part is to display the result
            //
            if (zeroDivision)
            {
               field.setText("Cannot divide by zero");
            }
            else
            {
               // Check if the result is an integer
               if (result % 1 == 0)
               {
                  // Use the DecimalFormat to remove the floating digits (e.g
                  // 12.0 -> 12)
                  field.setText("" + frm.format(result));

                  // We set it to false because we already have a result, that
                  // can act as number1 if we want to perform further
                  // calculations
                  tempResultActive = false;

                  // We reset the displayed string, so that when we
                  // press another number the string displays that one
                  // instead of appending it to the previous one
                  str = "";
               }
               else
               {
                  // If the result is a float, we don't need DecimalFormat
                  field.setText("" + (result));
                  tempResultActive = false;
                  str = "";
               }
            }
         }
      }
   }

   /**
    * Repeats the last calculation and returns the result.
    * 
    * @return result
    */
   public double repeatEquals()
   {
      // We need to read for the result, in case the user enters another number.
      // Then we can repeat the last calculation with the number entered instead
      // of the result
      double newResult = Double.parseDouble(field.getText());

      if (lastSign == '+')
      {
         newResult += number2;
      }
      else if (lastSign == '-')
      {
         newResult -= number2;
      }
      else if (lastSign == '/')
      {
         newResult /= number2;
      }
      else
      {
         newResult *= number2;
      }

      return newResult;
   }

   /**
    * Displays the result in the text field when you want to make multiple
    * calculations, before pressing the "equals" button.
    */
   public void tempResult()
   {
      if (!zeroDivision)
      {
         if (mathSign != ' ' && !str.equals(""))
         {
            if (mathSign == '+')
            {
               number2 = Double.parseDouble(field.getText());
               tempResult = number1 + number2;
               tempResultActive = false;
            }
            else if (mathSign == '-')
            {
               number2 = Double.parseDouble(field.getText());
               tempResult = number1 - number2;
               tempResultActive = false;
            }
            else if (mathSign == '/')
            {
               number2 = Double.parseDouble(field.getText());
               if (number2 == 0)
               {
                  zeroDivision = true;
               }
               else
               {
                  tempResult = number1 / number2;
                  tempResultActive = false;
               }
            }
            else if (mathSign == '*')
            {
               number2 = Double.parseDouble(field.getText());
               tempResult = number1 * number2;
               tempResultActive = false;
            }
         }
      }

      if (zeroDivision)
      {
         field.setText("Cannot divide by zero");
      }
      else
      {
         if (tempResult % 1 == 0)
         {
            str = "";
            field.setText("" + frm.format(tempResult));

         }
         else
         {
            str = "";
            field.setText("" + (tempResult));

         }
      }
   }

   public static void main(String[] args)
   {
      new Calculator();
   }
}
