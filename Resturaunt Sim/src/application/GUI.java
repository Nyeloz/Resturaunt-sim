package application;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel, popupPanel, gamePanel;
    private JLabel mainLabel;
    private static int clickCount = 0;
    String usrChoice = "";
    Random ran = new Random();
NPC1 npc = new NPC1();
    public GUI() {
        setTitle("Restaurant Simulator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel startPanel = createStartPanel();
        mainPanel.add(startPanel, "startPanel");

        gamePanel = createGamePanel();
        mainPanel.add(gamePanel, "gamePanel");

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createStartPanel() {
        JPanel startPanel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel("Restaurant Sim");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        startPanel.add(label, gbcLabel);

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50));
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 1;
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "gamePanel"));
        startPanel.add(startButton, gbcButton);

        return startPanel;
    }

    private JPanel createGamePanel() {
        JPanel gamePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints Dialoggbc = new GridBagConstraints();
        JLabel dialog = new JLabel("Welcome to the restaurant simulator");
        Dialoggbc.gridx = 1;
        Dialoggbc.gridy = 3;
        Dialoggbc.gridwidth = GridBagConstraints.REMAINDER; // Take up all remaining cells in the row
        Dialoggbc.fill = GridBagConstraints.HORIZONTAL;
        gamePanel.add(dialog, Dialoggbc);

        
        JButton next = new JButton("Next");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gamePanel.add(next, gbc);

        JButton popup = new JButton("Menu");
        gbc.gridx = 4;
        gbc.gridy = 4;
        gamePanel.add(popup, gbc);
        JButton choice1 = new JButton("Choice 1");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gamePanel.add(choice1, gbc);
        
        JButton choice2 = new JButton("Choice 2");
        gbc.gridx = 2;
        gbc.gridy = 5;
        gamePanel.add(choice2, gbc);
        
        JButton choice3 = new JButton("Choice 3");
        gbc.gridx = 3;
        gbc.gridy = 5;
        gamePanel.add(choice3, gbc);
        
       JLabel scoretxt = new JLabel("");
        	 gbc.gridx = 4;
             gbc.gridy = 0;
             gamePanel.add(scoretxt, gbc);
        next.addActionListener(e -> {
        	User usr = new User(); 
        	
             
            clickCount++;
            switch (clickCount) {
                case 1:
                    dialog.setText("Your aim is to make the randomly generated NPC or \"date\" happy. Click next to see the date's profile");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(gamePanel, "Name: " + npc.getName() + " is " + npc.getAge() + "\nLikes: " + npc.likes.get(0).name + ", " + npc.likes.get(1).name + ", " + npc.likes.get(2).name + "\nDislikes: " + npc.dislikes.get(0).name + ", " + npc.likes.get(1).name + ", " + npc.dislikes.get(2).name);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(gamePanel, "You're score will go up or down depending on the choices you make \n You're score is now 50, meaning neutral and the game begins now!");
                    break;
                case 4:
                	User.name = JOptionPane.showInputDialog(gamePanel, "Enter name");
                	dialog.setText(npc.getName() + ", walks up to the table. You nervously fix you clothing and straighten up your posture. \n \"Hello, I'm " + npc.getName() + "\". You smile back and respond: Hello, I'm " + User.name);
                	break;
                case 5:
                	scoretxt.setText("Your wallet currently has " + usr.getMoney());
                	dialog.setText("Now, this is am important first step. The waiter walks up to you and asks what you would like. \n You dont want to leave a bad impression on them, so what do you choose????");
                	choice3.setText(npc.likes.get(ran.nextInt(npc.likes.size())).name);
                	choice1.setEnabled(true);
                	choice2.setText(npc.dislikes.get(ran.nextInt(npc.dislikes.size())).name);
                	choice2.setEnabled(true);
                	choice1.setText(Menu.foodOptions[Math.max(ran.nextInt(Menu.foodOptions.length), 0)].name);
                	choice3.setEnabled(true);
                   
                    
                    choice1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	String buttonText = choice1.getText();
                            System.out.println("Button Text: " + buttonText); // Print button text
                            
                            // Print menu item names in likes list
                            System.out.println("Menu Item Names:");
                            for(Menu obj : npc.likes) {
                                System.out.println(obj.getName());
                            }
                            
                            // Find the item with the matching name in the menu
                            Menu selectedMenu = null;
                            for(Menu obj : Menu.foodOptions) {
                                if(obj.getName().equals(buttonText)) {
                                    selectedMenu = obj;
                                    break;
                                }
                            }
                            
                            // Check if the user has enough money to afford the choice
                            double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                            if (usr.getMoney() >= itemCost) {
                                // Deduct the cost from the user's bank account
                                User.money -= itemCost;
                                
                                // Proceed with the choice logic
                                for(Menu obj : npc.likes) {
                                    if(obj.getName().equals(buttonText)) {
                                        npc.loveMeter++;
                                        User.money+= 100;
                                        System.out.println(npc.loveMeter);
                                    }
                                }
                                for(Menu obj : npc.dislikes) {
                                    if(obj.getName().equals(buttonText)) {
                                        npc.loveMeter--;
                                        
                                        System.out.println(npc.loveMeter);
                                    }
                                }

                                if(npc.loveMeter < 3) {
                                    dialog.setText(npc.getName() + " frowns abit but moves on");
                                } else {
                                    dialog.setText(npc.getName() + " smiles happilly. How did you know i liked that dish! You smile bashfully and pumped your first in the air");
                                }
                            } else {
                                // Display a JOptionPane message dialog informing the user that they don't have enough money
                                JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    

                    choice2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Get the button text
                        	String buttonText = choice2.getText();
                            System.out.println("Button Text: " + buttonText); // Print button text
                            
                            // Print menu item names in likes list
                            System.out.println("Menu Item Names:");
                            for(Menu obj : Menu.foodOptions) {
                                System.out.println(obj.getName());
                            }
                            
                            // Find the item with the matching name in the menu
                            Menu selectedMenu = null;
                            for(Menu obj : Menu.foodOptions) {
                                if(obj.getName().equals(buttonText)) {
                                    selectedMenu = obj;
                                    break;
                                }
                            }
                            // Check if the user has enough money to afford the choice
                           double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                            if (usr.getMoney() >= itemCost) {
                                // Deduct the cost from the user's bank account
                                User.money -= itemCost;
                                
                                // Proceed with the choice logic
                                for(Menu obj : npc.likes) {
                                    if(obj.getName().equals(buttonText)) {
                                        npc.loveMeter++;
                                        User.money+= 100;
                                        System.out.println(npc.loveMeter);
                                    }
                                }
                                for(Menu obj : npc.dislikes) {
                                    if(obj.getName().equals(buttonText)) {
                                        npc.loveMeter--;
                                        System.out.println(npc.loveMeter);
                                    }
                                }

                                if(npc.loveMeter < 3) {
                                    dialog.setText(npc.getName() + " frowns abit but moves on");
                                } else {
                                    dialog.setText(npc.getName() + " smiles happilly. How did you know i liked that dish! You smile bashfully and pumped your first in the air");
                                }
                            } else {
                                // Display a JOptionPane message dialog informing the user that they don't have enough money
                                JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });


                    choice3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	String buttonText = choice3.getText();
                            System.out.println("Button Text: " + buttonText); // Print button text
                            
                            // Print menu item names in likes list
                            System.out.println("Menu Item Names:");
                            for(Menu obj : npc.likes) {
                                System.out.println(obj.getName());
                            }
                            
                            // Find the item with the matching name in the menu
                            Menu selectedMenu = null;
                            for(Menu obj : Menu.foodOptions) {
                                if(obj.getName().equals(buttonText)) {
                                    selectedMenu = obj;
                                    break;
                                	}
                            }
                            // Check if the user has enough money to afford the choice
                            double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                            if (usr.getMoney() >= itemCost) {
                                // Deduct the cost from the user's bank account
                                User.money -= itemCost;
                                
                                // Proceed with the choice logic
                                for(Menu obj : npc.likes) {
                                    if(obj.getName().equals(buttonText)) {
                                        npc.loveMeter++;
                                        User.money+= 100;
                                        System.out.println(npc.loveMeter);
                                    }
                                }
                                for(Menu obj : npc.dislikes) {
                                    if(obj.getName().equals(buttonText)) {
                                        npc.loveMeter--;
                                        System.out.println(npc.loveMeter);
                                    }
                                }

                                if(npc.loveMeter < 3) {
                                    dialog.setText(npc.getName() + " frowns abit but moves on");
                                } else {
                                    dialog.setText(npc.getName() + " smiles happilly. How did you know i liked that dish! You smile bashfully and pumped your first in the air");
                                }
                            } else {
                                // Display a JOptionPane message dialog informing the user that they don't have enough money
                                JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                	
             break;
             
          case 6:
        	  dialog.setText("Now that the entre was chosen, conversation flowed smoothly. Yall exchanged jokes and laughed but another challenge appeared. Main course!!! Its now time to choose");
        	  scoretxt.setText("Your wallet currently has " + usr.getMoney()); 
        	   // Set up choices for the main course
        	  choice1.setText(npc.likes.get(ran.nextInt(npc.likes.size())).name);
        	  choice1.setEnabled(true);
        	  choice2.setText(npc.dislikes.get(ran.nextInt(npc.dislikes.size())).name);
        	  choice2.setEnabled(true);
        	  choice3.setText(Menu.foodOptions[ran.nextInt(Menu.foodOptions.length)].name);
        	  choice3.setEnabled(true);
        	    System.out.println("Choice 1: " + choice1.getText());
        	    System.out.println("Choice 2: " + choice2.getText());
        	    System.out.println("Choice 3: " + choice3.getText());
        	    
        	    // ActionListener for choice1
        	    choice1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	String buttonText = choice1.getText();
                        System.out.println("Button Text: " + buttonText); // Print button text
                        
                        // Print menu item names in likes list
                        System.out.println("Menu Item Names:");
                        for(Menu obj : Menu.foodOptions) {
                            System.out.println(obj.getName());
                        }
                        
                        // Find the item with the matching name in the menu
                        Menu selectedMenu = null;
                        for(Menu obj : npc.likes) {
                            if(obj.getName().equals(buttonText)) {
                                selectedMenu = obj;
                                break;
                            }
                        }
                        
                        // Check if the user has enough money to afford the choice
                        double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                        if (usr.getMoney() >= itemCost) {
                            // Deduct the cost from the user's bank account
                            User.money -= itemCost;
                            
                            // Proceed with the choice logic
                            for(Menu obj : Menu.foodOptions) {
                                if(obj.getName().equals(buttonText)) {
                                    npc.loveMeter++;
                                    User.money+= 100;
                                    System.out.println(npc.loveMeter);
                                }
                            }
                            for(Menu obj : npc.dislikes) {
                                if(obj.getName().equals(buttonText)) {
                                    npc.loveMeter--;
                                    System.out.println(npc.loveMeter);
                                }
                            }

                            if(npc.loveMeter < 3) {
                                dialog.setText(npc.getName() + " frowns abit but moves on");
                            } else {
                                dialog.setText(npc.getName() + " smiles happilly. How did you know i liked that dish! You smile bashfully and pumped your first in the air");
                            }
                        } else {
                            // Display a JOptionPane message dialog informing the user that they don't have enough money
                            JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                

                choice2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	String buttonText = choice2.getText();
                        System.out.println("Button Text: " + buttonText); // Print button text
                        
                        // Print menu item names in likes list
                        System.out.println("Menu Item Names:");
                        for(Menu obj : 	Menu.foodOptions) {
                            System.out.println(obj.getName());
                        }
                        
                        // Find the item with the matching name in the menu
                        Menu selectedMenu = null;
                        for(Menu obj : Menu.foodOptions) {
                            if(obj.getName().equals(buttonText)) {
                                selectedMenu = obj;
                                break;
                            }
                        }
                        
                        // Check if the user has enough money to afford the choice
                       double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                        if (usr.getMoney() >= itemCost) {
                            // Deduct the cost from the user's bank account
                            User.money -= itemCost;
                            
                            // Proceed with the choice logic
                            for(Menu obj : npc.likes) {
                                if(obj.getName().equals(buttonText)) {
                                    npc.loveMeter++;
                                    User.money+= 100;
                                    System.out.println(npc.loveMeter);
                                }
                            }
                            for(Menu obj : npc.dislikes) {
                                if(obj.getName().equals(buttonText)) {
                                    npc.loveMeter--;
                                    System.out.println(npc.loveMeter);
                                }
                            }

                            if(npc.loveMeter < 3) {
                                dialog.setText(npc.getName() + " side eyed you");
                            } else {
                                dialog.setText(npc.getName() + " giggled and rested her hand on your shoulder");
                            }
                        } else {
                            // Display a JOptionPane message dialog informing the user that they don't have enough money
                            JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });


                choice3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	String buttonText = choice3.getText();
                        System.out.println("Button Text: " + buttonText); // Print button text
                        
                        // Print menu item names in likes list
                        System.out.println("Menu Item Names:");
                        for(Menu obj : npc.likes) {
                            System.out.println(obj.getName());
                        }
                        
                        // Find the item with the matching name in the menu
                        Menu selectedMenu = null;
                        for(Menu obj :Menu.foodOptions) {
                            if(obj.getName().equals(buttonText)) {
                                selectedMenu = obj;
                                break;
                            }
                        }
                        
                        // Check if the user has enough money to afford the choice
                        double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                        if (usr.getMoney() >= itemCost) {
                            // Deduct the cost from the user's bank account
                            User.money -= itemCost;
                            
                            // Proceed with the choice logic
                            for(Menu obj : npc.likes) {
                                if(obj.getName().equals(buttonText)) {
                                    npc.loveMeter++;
                                    User.money+= 100;
                                    System.out.println(npc.loveMeter);
                                }
                            }
                            for(Menu obj : npc.dislikes) {
                                if(obj.getName().equals(buttonText)) {
                                    npc.loveMeter--;
                                    System.out.println(npc.loveMeter);
                                }
                            }

                            if(npc.loveMeter < 3) {
                                dialog.setText(npc.getName() + " side eyed you");
                            } else {
                                dialog.setText(npc.getName() + " giggled and placed her hand on your shoudler");
                            }
                        } else {
                            // Display a JOptionPane message dialog informing the user that they don't have enough money
                            JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
        	   
            break;
           
          case 7:
        	  scoretxt.setText("Your wallet currently has " + usr.getMoney());
        	  dialog.setText("With your stomachs full and smiles on yalls face, you comment you could go for desert. " + npc.getName() + " agrees and says to choose. They eye you and it's like if you choose wrong this whole date can end!!!!!");
        	  choice2.setText(npc.likes.get(ran.nextInt(npc.likes.size())).name);
        	  choice1.setEnabled(true);
        	  choice3.setText(npc.dislikes.get(ran.nextInt(npc.dislikes.size())).name);
        	  choice2.setEnabled(true);
        	  choice1.setText(Menu.foodOptions[ran.nextInt(Menu.foodOptions.length)].name);
        	  choice3.setEnabled(true);
              choice1.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                	 String buttonText = choice1.getText();
                      System.out.println("Button Text: " + buttonText); // Print button text
                      
                      // Print menu item names in likes list
                      System.out.println("Menu Item Names:");
                      for(Menu obj : npc.likes) {
                          System.out.println(obj.getName());
                      }
                      
                      // Find the item with the matching name in the menu
                      Menu selectedMenu = null;
                      for(Menu obj : Menu.foodOptions) {
                          if(obj.getName().equals(buttonText)) {
                              selectedMenu = obj;
                              break;
                          }
                      }
                      // Check if the user has enough money to afford the choice
                      double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                      if (usr.getMoney() >= itemCost) {
                          // Deduct the cost from the user's bank account
                          User.money -= itemCost;
                          
                          // Proceed with the choice logic
                          for(Menu obj : npc.likes) {
                              if(obj.getName().equals(buttonText)) {
                                  npc.loveMeter++;
                                  User.money+= 100;
                                  System.out.println(npc.loveMeter);
                              }
                          }
                          for(Menu obj : npc.dislikes) {
                              if(obj.getName().equals(buttonText)) {
                                  npc.loveMeter--;
                                  System.out.println(npc.loveMeter);
                              }
                          }

                          if(npc.loveMeter < 3) {
                              dialog.setText(npc.getName() + " side eyed you");
                          } else {
                              dialog.setText(npc.getName() + " giggled and placed their hand on your shoudler");
                          }
                      } else {
                          // Display a JOptionPane message dialog informing the user that they don't have enough money
                          JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });

              

              choice2.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                	  String buttonText = choice2.getText();
                      System.out.println("Button Text: " + buttonText); // Print button text
                      
                      // Print menu item names in likes list
                      System.out.println("Menu Item Names:");
                      for(Menu obj : Menu.foodOptions) {
                          System.out.println(obj.getName());
                      }
                      
                      // Find the item with the matching name in the menu
                      Menu selectedMenu = null;
                      for(Menu obj : Menu.foodOptions) {
                          if(obj.getName().equals(buttonText)) {
                              selectedMenu = obj;
                              break;
                          }
                      }
                      
                      // Check if the user has enough money to afford the choice
                     double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                      if (usr.getMoney() >= itemCost) {
                          // Deduct the cost from the user's bank account
                          User.money -= itemCost;
                          
                          // Proceed with the choice logic
                          for(Menu obj : npc.likes) {
                              if(obj.getName().equals(buttonText)) {
                                  npc.loveMeter++;
                                  User.money+= 100;
                                  System.out.println(npc.loveMeter);
                              }
                          }
                          for(Menu obj : npc.dislikes) {
                              if(obj.getName().equals(buttonText)) {
                                  npc.loveMeter--;
                                  System.out.println(npc.loveMeter);
                              }
                          }

                          if(npc.loveMeter < 3) {
                              dialog.setText(npc.getName() + " slaps and leaves you");
                          } else {
                              dialog.setText(npc.getName() + " kisses you on the cheek");
                          }
                      } else {
                          // Display a JOptionPane message dialog informing the user that they don't have enough money
                          JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });


              choice3.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                	  String buttonText = choice3.getText();
                      System.out.println("Button Text: " + buttonText); // Print button text
                      
                      // Print menu item names in likes list
                      System.out.println("Menu Item Names:");
                      for(Menu obj : Menu.foodOptions) {
                          System.out.println(obj.getName());
                      }
                      
                      // Find the item with the matching name in the menu
                      Menu selectedMenu = null;
                      for(Menu obj : Menu.foodOptions) {
                          if(obj.getName().equals(buttonText)) {
                              selectedMenu = obj;
                              break;
                          }
                      }
                      
                      // Check if the user has enough money to afford the choice
                      double itemCost = selectedMenu.getPrice(); // Assuming getCost() returns the cost of the menu item
                      if (usr.getMoney() >= itemCost) {
                          // Deduct the cost from the user's bank account
                          User.money -= itemCost;
                          
                          // Proceed with the choice logic
                          for(Menu obj : npc.likes) {
                              if(obj.getName().equals(buttonText)) {
                                  npc.loveMeter++;
                                  User.money+= 100;
                                  System.out.println(npc.loveMeter);
                              }
                          }
                          for(Menu obj : npc.dislikes) {
                              if(obj.getName().equals(buttonText)) {
                                  npc.loveMeter--;
                                  System.out.println(npc.loveMeter);
                              }
                          }

                          if(npc.loveMeter < 3) {
                              dialog.setText(npc.getName() + " slaps and leaves you");
                          } else {
                              dialog.setText(npc.getName() + " kisses you on the cheek ");
                          }
                      } else {
                          // Display a JOptionPane message dialog informing the user that they don't have enough money
                          JOptionPane.showMessageDialog(gamePanel, "Sorry, you don't have enough money to afford this choice.", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              });
              break;
          case 8:
        	  	if(npc.loveMeter < 4) {
        	  		dialog.setText("You sit in your chair lonely");
        	  	}else {
        	  		dialog.setText("You both walk off hand in hand in the sunset");
        	  	}
             break;
            
            }
           
        });
        
        choice1.setEnabled(false);
        choice2.setEnabled(false);
        choice3.setEnabled(false);
        popup.addActionListener(e -> showPopup());

        return gamePanel;
    }

    private void showPopup() {
        JFrame popupFrame = new JFrame("Menu");
        popupFrame.setSize(300, 200);
        popupPanel = new JPanel(new GridBagLayout());

        JButton backButton = new JButton("Back to Main Page");
        backButton.addActionListener(e -> popupFrame.dispose());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        popupPanel.add(backButton, gbc);

        JTextArea textArea = new JTextArea("Truffle-infused Jollof Rice with Lobster Tail - $75.00\\r\\n\"\r\n"
        		+ "	        		+ \"Wagyu Beef Egusi Soup - $120.00\\r\\n\"\r\n"
        		+ "	        		+ \"Plantain and Foie Gras Suya Skewers - $90.00\\r\\n\"\r\n"
        		+ "	        		+ \"Grilled Oxtail with Black Truffle Butter - $150.00\\r\\n\"\r\n"
        		+ "	        		+ \"Seafood Gumbo with King Crab and African Peri-Peri Sauce - $110.00\\r\\n\"\r\n"
        		+ "	        		+ \"Okra Gnocchi with White Truffle Oil - $85.00\\r\\n\"\r\n"
        		+ "	        		+ \"Braised Goat Shank in Moroccan Spices - $130.00\\r\\n\"\r\n"
        		+ "	        		+ \"African Quail Stuffed with Wild Rice and Pomegranate Glaze - $95.00\\r\\n\"\r\n"
        		+ "	        		+ \"Lobster Fufu with Champagne-Infused Palm Nut Soup - $180.00\\r\\n\"\r\n"
        		+ "	        		+ \"Exotic Mushroom and Plantain Risotto - $100.00\\r\\n\"\r\n"
        		+ "	        		+ \"Jollof Rice with Grilled Chicken - $15.00\\r\\n\"\r\n"
        		+ "	        		+ \"Fried Catfish with Plantain Chips - $18.00\\r\\n\"\r\n"
        		+ "	        		+ \"Vegetable Suya Skewers - $12.00\\r\\n\"\r\n"
        		+ "	        		+ \"Tomato and Okra Stew with Rice - $14.00\\r\\n\"\r\n"
        		+ "	        		+ \"Nigerian Meat Pie - $5.00\\r\\n\"\r\n"
        		+ "	        		+ \"Caviar-Infused Fufu with Gold Leaf Garnish - $250.00\\r\\n\"\r\n"
        		+ "	        		+ \"Bison and Plantain Puff-Puff Sliders - $120.00\\r\\n\"\r\n"
        		+ "	        		+ \"Truffle Mac and Cheese with Smoked Duck - $95.00\\r\\n\"\r\n"
        		+ "	        		+ \"Pan-Seared Red Snapper with Moringa Pesto - $140.00\\r\\n\"\r\n"
        		+ "	        		+ \"Goat Cheese and Fig Stuffed Akara Balls - $80.00\\r\\n\"\r\n"
        		+ "	        		+ \"Spicy Lamb Jollof Risotto - $110.00\\r\\n\"\r\n"
        		+ "	        		+ \"Lobster Efo Riro (Spinach Stew) - $160.00\\r\\n\"\r\n"
        		+ "	        		+ \"Quinoa Jollof with Braised Short Rib - $130.00\\r\\n\"\r\n"
        		+ "	        		+ \"Saffron-Infused Eba (Garri) with Grilled Prawns - $100.00\\r\\n\"\r\n"
        		+ "	        		+ \"Coconut-Curry Goat Ravioli - $125.00\\r\\n\"\r\n"
        		+ "	        		+ \"Foie Gras and Plantain Moin Moin - $135.00\\r\\n\"\r\n"
        		+ "	        		+ \"Berbere-Spiced Venison Suya - $145.00\\r\\n\"\r\n"
        		+ "	        		+ \"Turmeric-Infused Yam Porridge with Lobster - $170.00\\r\\n\"\r\n"
        		+ "	        		+ \"Peri-Peri Chicken Livers with Caramelized Onions - $85.00\\r\\n\"\r\n"
        		+ "	        		+ \"Smoked Turkey and Collard Green Spring Rolls - $75.00\\r\\n\"\r\n"
        		+ "	        		+ \" \"");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        popupPanel.add(new JScrollPane(textArea), gbc);

        popupFrame.add(popupPanel);
        popupFrame.setVisible(true);
    }

}



