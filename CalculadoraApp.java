import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraApp extends JFrame implements ActionListener {

    // === Operaciones matemáticas ===
    private JTextField txtNum1, txtNum2;
    private JLabel lblResultMath;
    private JButton btnSumar, btnRestar, btnMultiplicar, btnDividir;

    // === Conversión de temperatura ===
    private JTextField txtTemp;
    private JLabel lblResultTemp;
    private JButton btnCtoF, btnFtoC;

    // === Conversión de moneda ===
    private JTextField txtMoneda;
    private JLabel lblResultMoneda;
    private JButton btnUSDtoCOP, btnCOPtoUSD;

    private static final double TASA_CAMBIO = 3800.0;

    private static final Color COLOR_PRIMARIO   = new Color(41, 128, 185);
    private static final Color COLOR_RESULTADO  = new Color(39, 174, 96);
    private static final Color COLOR_FONDO      = new Color(245, 246, 250);
    private static final Color COLOR_PANEL      = Color.WHITE;

    public CalculadoraApp() {
        setTitle("Calculadora y Convertidor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 680);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(COLOR_FONDO);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(COLOR_FONDO);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título general
        JLabel titulo = new JLabel("Calculadora y Convertidor", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(COLOR_PRIMARIO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titulo);
        mainPanel.add(Box.createVerticalStrut(12));

        mainPanel.add(crearSeccionMatematica());
        mainPanel.add(Box.createVerticalStrut(12));
        mainPanel.add(crearSeccionTemperatura());
        mainPanel.add(Box.createVerticalStrut(12));
        mainPanel.add(crearSeccionMoneda());

        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.setBorder(null);
        add(scroll);
    }

    // ---------------------------------------------------------------
    // SECCIÓN 1 – Operaciones matemáticas
    // ---------------------------------------------------------------
    private JPanel crearSeccionMatematica() {
        JPanel panel = crearPanelSeccion("  Operaciones Matemáticas");

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        // Número 1
        g.gridx = 0; g.gridy = 0; g.gridwidth = 1; g.weightx = 0;
        panel.add(etiqueta("Número 1:"), g);
        g.gridx = 1; g.weightx = 1;
        txtNum1 = campoTexto("Ingrese el primer número");
        panel.add(txtNum1, g);

        // Número 2
        g.gridx = 0; g.gridy = 1; g.weightx = 0;
        panel.add(etiqueta("Número 2:"), g);
        g.gridx = 1; g.weightx = 1;
        txtNum2 = campoTexto("Ingrese el segundo número");
        panel.add(txtNum2, g);

        // Botones
        btnSumar      = crearBoton("Sumar");
        btnRestar     = crearBoton("Restar");
        btnMultiplicar = crearBoton("Multiplicar");
        btnDividir    = crearBoton("Dividir");

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        btns.setBackground(COLOR_PANEL);
        btns.add(btnSumar); btns.add(btnRestar);
        btns.add(btnMultiplicar); btns.add(btnDividir);

        g.gridx = 0; g.gridy = 2; g.gridwidth = 2; g.weightx = 1;
        panel.add(btns, g);

        // Resultado
        g.gridy = 3;
        panel.add(filaResultado("Resultado:", lblResultMath = labelResultado()), g);

        return panel;
    }

    // ---------------------------------------------------------------
    // SECCIÓN 2 – Conversión de temperatura
    // ---------------------------------------------------------------
    private JPanel crearSeccionTemperatura() {
        JPanel panel = crearPanelSeccion("  Conversión de Temperatura");

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0; g.gridwidth = 1; g.weightx = 0;
        panel.add(etiqueta("Temperatura:"), g);
        g.gridx = 1; g.weightx = 1;
        txtTemp = campoTexto("Ingrese el valor de temperatura");
        panel.add(txtTemp, g);

        btnCtoF = crearBoton("°C  →  °F");
        btnFtoC = crearBoton("°F  →  °C");

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        btns.setBackground(COLOR_PANEL);
        btns.add(btnCtoF); btns.add(btnFtoC);

        g.gridx = 0; g.gridy = 1; g.gridwidth = 2;
        panel.add(btns, g);

        g.gridy = 2;
        panel.add(filaResultado("Resultado:", lblResultTemp = labelResultado()), g);

        return panel;
    }

    // ---------------------------------------------------------------
    // SECCIÓN 3 – Conversión de moneda
    // ---------------------------------------------------------------
    private JPanel crearSeccionMoneda() {
        JPanel panel = crearPanelSeccion("  Conversión de Moneda  (Tasa fija: $3.800 COP por 1 USD)");

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0; g.gridy = 0; g.gridwidth = 1; g.weightx = 0;
        panel.add(etiqueta("Valor:"), g);
        g.gridx = 1; g.weightx = 1;
        txtMoneda = campoTexto("Ingrese el monto a convertir");
        panel.add(txtMoneda, g);

        btnUSDtoCOP = crearBoton("USD  →  COP");
        btnCOPtoUSD = crearBoton("COP  →  USD");

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        btns.setBackground(COLOR_PANEL);
        btns.add(btnUSDtoCOP); btns.add(btnCOPtoUSD);

        g.gridx = 0; g.gridy = 1; g.gridwidth = 2;
        panel.add(btns, g);

        g.gridy = 2;
        panel.add(filaResultado("Resultado:", lblResultMoneda = labelResultado()), g);

        return panel;
    }

    // ---------------------------------------------------------------
    // ActionListener central
    // ---------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnSumar || src == btnRestar || src == btnMultiplicar || src == btnDividir) {
            operacionMatematica(src);
        } else if (src == btnCtoF || src == btnFtoC) {
            convertirTemperatura(src);
        } else if (src == btnUSDtoCOP || src == btnCOPtoUSD) {
            convertirMoneda(src);
        }
    }

    // ---------------------------------------------------------------
    // Lógica – Operaciones matemáticas
    // ---------------------------------------------------------------
    private void operacionMatematica(Object src) {
        String s1 = txtNum1.getText().trim();
        String s2 = txtNum2.getText().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            advertencia("Debe ingresar ambos números en los campos 'Número 1' y 'Número 2'.");
            return;
        }

        double a, b;
        try { a = Double.parseDouble(s1); }
        catch (NumberFormatException ex) {
            error("El campo 'Número 1' contiene caracteres no válidos. Solo ingrese números.");
            return;
        }
        try { b = Double.parseDouble(s2); }
        catch (NumberFormatException ex) {
            error("El campo 'Número 2' contiene caracteres no válidos. Solo ingrese números.");
            return;
        }

        double resultado;

        if (src == btnSumar) {
            resultado = a + b;
        } else if (src == btnRestar) {
            resultado = a - b;
        } else if (src == btnMultiplicar) {
            resultado = a * b;
        } else {
            if (b == 0) {
                error("División por cero no permitida. Ingrese un 'Número 2' distinto de cero.");
                return;
            }
            resultado = a / b;
        }

        lblResultMath.setText(formatearNumero(resultado));
    }

    // ---------------------------------------------------------------
    // Lógica – Temperatura
    // ---------------------------------------------------------------
    private void convertirTemperatura(Object src) {
        String s = txtTemp.getText().trim();

        if (s.isEmpty()) {
            advertencia("Ingrese la temperatura en el campo 'Temperatura'.");
            return;
        }

        double temp;
        try { temp = Double.parseDouble(s); }
        catch (NumberFormatException ex) {
            error("El campo 'Temperatura' contiene caracteres no válidos. Solo ingrese números.");
            return;
        }

        if (src == btnCtoF) {
            double f = (temp * 9.0 / 5.0) + 32.0;
            lblResultTemp.setText(String.format("%.2f °F", f));
        } else {
            double c = (temp - 32.0) * 5.0 / 9.0;
            lblResultTemp.setText(String.format("%.2f °C", c));
        }
    }

    // ---------------------------------------------------------------
    // Lógica – Moneda
    // ---------------------------------------------------------------
    private void convertirMoneda(Object src) {
        String s = txtMoneda.getText().trim();

        if (s.isEmpty()) {
            advertencia("Ingrese el monto en el campo 'Valor'.");
            return;
        }

        double valor;
        try { valor = Double.parseDouble(s); }
        catch (NumberFormatException ex) {
            error("El campo 'Valor' contiene caracteres no válidos. Solo ingrese números.");
            return;
        }

        if (src == btnUSDtoCOP) {
            double cop = valor * TASA_CAMBIO;
            lblResultMoneda.setText(String.format("$ %,.2f COP", cop));
        } else {
            double usd = valor / TASA_CAMBIO;
            lblResultMoneda.setText(String.format("$ %.4f USD", usd));
        }
    }

    // ---------------------------------------------------------------
    // Helpers de UI
    // ---------------------------------------------------------------
    private JPanel crearPanelSeccion(String titulo) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARIO, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        TitledBorder tb = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_PRIMARIO, 1, true), titulo);
        tb.setTitleFont(new Font("SansSerif", Font.BOLD, 13));
        tb.setTitleColor(COLOR_PRIMARIO);
        panel.setBorder(tb);

        return panel;
    }

    private JLabel etiqueta(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
        return l;
    }

    private JTextField campoTexto(String placeholder) {
        JTextField tf = new JTextField(18);
        tf.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tf.setToolTipText(placeholder);
        return tf;
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBackground(Color.WHITE);
        btn.setForeground(COLOR_PRIMARIO);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        return btn;
    }

    private JLabel labelResultado() {
        JLabel l = new JLabel("—");
        l.setFont(new Font("SansSerif", Font.BOLD, 16));
        l.setForeground(COLOR_RESULTADO);
        return l;
    }

    private JPanel filaResultado(String etiq, JLabel valor) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        fila.setBackground(COLOR_PANEL);
        fila.add(etiqueta(etiq));
        fila.add(valor);
        return fila;
    }

    private String formatearNumero(double n) {
        if (n == Math.floor(n) && !Double.isInfinite(n)) {
            return String.format("%,.0f", n);
        }
        return String.format("%,.4f", n).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    private void advertencia(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Campo vacío", JOptionPane.WARNING_MESSAGE);
    }

    private void error(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error de entrada", JOptionPane.ERROR_MESSAGE);
    }

    // ---------------------------------------------------------------
    // Main
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new CalculadoraApp().setVisible(true);
        });
    }
}
