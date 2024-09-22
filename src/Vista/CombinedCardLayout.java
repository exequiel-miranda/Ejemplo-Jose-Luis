package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CombinedCardLayout extends JFrame {

    private JPanel cardPanel;
    private JPanel detailPanel;
    private JTextField hotelNameField;
    private JButton addButton;
    public CombinedCardLayout() {
        setTitle("Interfaz de Cards Personalizada");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal de la aplicación
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel para las cards
        cardPanel = new JPanel(new GridLayout(0, 4, 15, 15)); // Organiza las cards en una cuadrícula
        JScrollPane scrollPane = new JScrollPane(cardPanel);

        // Panel de detalles a la derecha
        detailPanel = new JPanel();
        detailPanel.setPreferredSize(new Dimension(300, 0));
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear las secciones iniciales
        addSection("Hospedajes", "Hotel mar y arena");
        addSection("Restaurantes", "Restaurante La Playa");
        addSection("Lugares Turísticos", "Parque Nacional");

        // Panel que contiene las cards a la izquierda
        JPanel cardsContainer = new JPanel();
        cardsContainer.setLayout(new BorderLayout());
        cardsContainer.add(scrollPane, BorderLayout.CENTER);

        // Agregar todo al layout principal
        mainPanel.add(cardsContainer, BorderLayout.CENTER);
        mainPanel.add(detailPanel, BorderLayout.EAST);

        // Formulario para agregar nuevas cards
        JPanel formPanel = new JPanel(new FlowLayout());
        hotelNameField = new JTextField(20);
        addButton = new JButton("Agregar Card");

        formPanel.add(new JLabel("Nombre del lugar:"));
        formPanel.add(hotelNameField);
        formPanel.add(addButton);

        // Agregar funcionalidad para el botón de agregar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hotelName = hotelNameField.getText();
                if (!hotelName.isEmpty()) {
                    addCard(hotelName); // Añadir una nueva card
                    hotelNameField.setText(""); // Limpiar campo de texto
                }
            }
        });

        // Añadir el formulario al panel principal (abajo)
        mainPanel.add(formPanel, BorderLayout.SOUTH);

        // Mostrar el frame
        setContentPane(mainPanel);
    }

    // Método para agregar secciones iniciales
    private void addSection(String sectionName, String cardTitle) {
        // Título de la sección
        JLabel sectionLabel = new JLabel(sectionName);
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sectionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cardPanel.add(sectionLabel);

        // Añadir algunas cards para cada sección
        for (int i = 0; i < 3; i++) {
            addCard(cardTitle);
        }
    }

    // Método para crear una card
    private void addCard(String title) {
        // Panel para la card
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(new Color(255, 250, 240)); // Fondo similar al ejemplo

        // Imagen ficticia (puedes cambiar esto por una real)
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Vista/imagen.jpg"));
        JLabel imageLabel = new JLabel(imageIcon);

        // Nombre del lugar
        JLabel nameLabel = new JLabel(title, JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Botón "ver detalles"
        JButton detailButton = new JButton("Ver detalles");
        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails(title, imageIcon); // Muestra detalles cuando se pulsa
            }
        });

        // Estilizar y organizar los elementos dentro de la card
        card.add(imageLabel, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.NORTH);
        card.add(detailButton, BorderLayout.SOUTH);

        // Añadir la card al panel de cards
        cardPanel.add(card);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    // Mostrar detalles de la card seleccionada
    private void showDetails(String title, ImageIcon image) {
        detailPanel.removeAll();

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel imageLabel = new JLabel(image);

        JTextArea descriptionArea = new JTextArea(5, 20);
        descriptionArea.setText("Descripción del " + title);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);

        // Agregar elementos al panel de detalles
        detailPanel.add(titleLabel);
        detailPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailPanel.add(imageLabel);
        detailPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailPanel.add(descriptionArea);

        detailPanel.revalidate();
        detailPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CombinedCardLayout().setVisible(true);
        });
    }
}
