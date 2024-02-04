import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterScreen extends JFrame {
    //extend - parmantot no JFrame(Swing) komponentus

    //mainigie ir nodefineti
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField fullnameField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton backButton;
    private JTextField cityField;
    private JTextField countryField;
    private JTextField adressField;


    /*constructor metode(metode kas, pec nosaukuma ir tada pati kaa klase.
     Ipatniba, kad inicializejam objektu, ta metode automatiski nostrada */
    public RegisterScreen(){
        //loga nosaukums, izmers, tas viss ir funkcijas
        setTitle("Register screen");
        setSize(350, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //kur logs atveraas valjaa, ja null, tad paradisies centraa
        setLayout(null);

        createUI();
        //ar so funkciju, mes izsaucam otru funkciju

    }

    //create UI metode (liksim ieksaa visus komponentus)
    public void createUI(){

        //username
        //nosaukums input laukam
        JLabel userLabel = new JLabel("Username: ");
        //seit arii nostradaas konstruktor metode
        userLabel.setBounds(10, 20, 80, 25);
        add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(120,20,165,25);
        add(usernameField);

        //password
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 55,80,25);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(120,55,165,25);
        add(passwordField);

        // fullname

        JLabel nameLabel = new JLabel("Full name: ");
        nameLabel.setBounds(10,90,80,25);
        add(nameLabel);

        fullnameField = new JTextField(20);
        fullnameField.setBounds(120,90,165,25);
        add(fullnameField);

        // email
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(10,125,80,25);
        add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(120,125,165,25);
        add(emailField);

        // city
        JLabel cityLabel = new JLabel("City: ");
        cityLabel.setBounds(10,160,80,25);
        add(cityLabel);

        cityField = new JTextField(20);
        cityField.setBounds(120,160,165,25);
        add(cityField);

        // country
        JLabel countryLabel = new JLabel("Country: ");
        countryLabel.setBounds(10,195,80,25);
        add(countryLabel);

        countryField = new JTextField(20);
        countryField.setBounds(120,195,165,25);
        add(countryField);

        // adress
        JLabel adressLabel = new JLabel("Adress (street): ");
        adressLabel.setBounds(10,230,100,25);
        add(adressLabel);

        adressField = new JTextField(20);
        adressField.setBounds(120,230,165,25);
        add(adressField);

        // register button
        registerButton = new JButton("Register");
        registerButton.setBounds(120, 265, 90, 25 );
        add(registerButton);

        //back button
        backButton = new JButton("Back");
        //vajag uztaisit back to login screen
        backButton.setBounds(120,300,70,25);
        add(backButton);

        registerButton.addActionListener(e -> performRegistration());
        //nospiezot pogu, strada Landa funkcija, kas izsauc citu funkciju

    }

    // buttons action ->sql
    private void performRegistration(){
        String username = usernameField.getText();
        //usernameField(input lauks).Mums ir jadabuj vertiba un jasaglavaa mainigajaa
        //drosak saglaba mainigajaa
        //username pieder sai funkcijai, mes to definejam seit

        String password = new String(passwordField.getPassword());
        //jo password ar punktiniem, nevis ar burtiem

        String fullname = fullnameField.getText();
        String email = emailField.getText();
        String city = cityField.getText();
        String country = countryField.getText();
        String adress = adressField.getText();

        //parbaude, vai tajaa vertibaa ir pievienots kaut kas
        //check
        if(username.isEmpty() || password.isEmpty() || fullname.isEmpty() || email.isEmpty() || city.isEmpty() || country.isEmpty() || adress.isEmpty()){
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            //lai citi procesi ari apstajas,ja ir kluda - return
        }

        //insert query -> SQL
        String sql = "INSERT INTO users (username, password, fullname, email, city, country, adress) VALUES (?, ?, ?, ?, ?, ?, ?)";

        //aktivizejam savienojumu
        //try - rakstam, ko mes gribam darit; catch - apstradasim error, kas var paradities
        try (Connection conn = DatabaseConnection.getConnection();
             // prepared statement (lidz sim izmantojam parasto stmt, tagad pstmt
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, fullname);
            pstmt.setString(4, email);
            pstmt.setString(5, city);
            pstmt.setString(6, country);
            pstmt.setString(7, adress);

            //parametra index - pozicija; seit aprakstam, ko ievadit tur, kur bija ? zimes
            //1 pozicija - 1.?, vertiba - username (ko nodeklarejam tiesi sajaa metodee PerformRegistration)

            int effectedRows = pstmt.executeUpdate();
            //vins izpilda (executeUpdate), to izpildi saglabajot mainigajaa effectedRows

            if (effectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Registration Succesfull!");
                //this - kur japaradas atseviskam logam ar message? logam jabut relativam pret parent
            } else {
                JOptionPane.showMessageDialog(this, "Registraion Failed!", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (SQLException | ClassNotFoundException ex){
            //getConnect u.c. lidz sim pasvitrojas ar sarkano, jo neapstradajam exceptions
            JOptionPane.showMessageDialog(this, "Error during registration: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

}
