package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardLayoutExample extends JFrame {

    // Panel principal que contendrá las cards
    private JPanel cardPanel;
    private JTextField hotelNameField;
    private JButton addButton;

    public CardLayoutExample() {
        setTitle("Ejemplo de Cards");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del panel principal
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columnas, ajustable
        JScrollPane scrollPane = new JScrollPane(cardPanel);

        // Formulario para agregar nuevos hoteles
        hotelNameField = new JTextField(20);
        addButton = new JButton("Agregar Card");

        // Panel para el formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout());
        formPanel.add(new JLabel("Nombre del hotel:"));
        formPanel.add(hotelNameField);
        formPanel.add(addButton);

        // Acción al presionar el botón
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = hotelNameField.getText();
                if (!hotelName.isEmpty()) {
                    addCard(hotelName); // Llamada para agregar una nueva card
                    hotelNameField.setText(""); // Limpiar campo de texto
                }
            }
        });

        // Layout del Frame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }

    // Método para crear una card
// Método para crear una card
    private void addCard(String hotelName) {
        // Panel para la card
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Cargar la imagen desde el paquete "Vista"
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Vista/imagen.jpg"));
        JLabel imageLabel = new JLabel(imageIcon); // Mostrar la imagen
        JLabel nameLabel = new JLabel(hotelName, JLabel.CENTER);

        // Estilizar card
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.SOUTH);

        // Añadir la card al panel principal
        cardPanel.add(card);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CardLayoutExample().setVisible(true);
        });
    }
}
