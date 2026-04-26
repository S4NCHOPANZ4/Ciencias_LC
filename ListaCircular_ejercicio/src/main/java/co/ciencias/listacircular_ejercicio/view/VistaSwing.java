package co.ciencias.listacircular_ejercicio.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaSwing extends JFrame {

    public JTextField   campoJugadores;
    public JButton      btnIniciar;
    public JButton      btnSiguiente;
    public JLabel       lblInfo;
    public JTextArea    logArea;
    public TableroPanel tablero;

    public VistaSwing() {
        setTitle("Juego Lista Circular");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        // Panel superior
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTop.add(new JLabel("Jugadores:"));
        campoJugadores = new JTextField("5", 5);
        panelTop.add(campoJugadores);
        btnIniciar = new JButton("Iniciar");
        panelTop.add(btnIniciar);
        btnSiguiente = new JButton("Siguiente turno");
        btnSiguiente.setEnabled(false);
        panelTop.add(btnSiguiente);
        lblInfo = new JLabel("  Presiona Iniciar para comenzar.");
        panelTop.add(lblInfo);
        add(panelTop, BorderLayout.NORTH);

        // Tablero
        tablero = new TableroPanel();
        add(tablero, BorderLayout.CENTER);

        // Log
        logArea = new JTextArea(10, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(logArea), BorderLayout.EAST);

        setVisible(true);
    }

    public void log(String msg) {
        logArea.append(msg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public int getCantidad() {
        return Integer.parseInt(campoJugadores.getText().trim());
    }

    // dibuja lista 
    public static class TableroPanel extends JPanel {

        private List<Integer> jugadores;
        private int actualId = -1;

        public void actualizar(List<Integer> jugadores, int actualId) {
            this.jugadores = jugadores;
            this.actualId  = actualId;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (jugadores == null || jugadores.isEmpty()) return;

            int cx    = getWidth()  / 2;
            int cy    = getHeight() / 2;
            int n     = jugadores.size();
            int radio = (int)(Math.min(cx, cy) * 0.75);
            int r     = 22;

            int[] xs = new int[n];
            int[] ys = new int[n];

            for (int i = 0; i < n; i++) {
                double angulo = -Math.PI / 2 + 2 * Math.PI * i / n;
                xs[i] = cx + (int)(radio * Math.cos(angulo));
                ys[i] = cy + (int)(radio * Math.sin(angulo));
            }

            // entre nodos
            g.setColor(Color.GRAY);
            for (int i = 0; i < n; i++) {
                int sig = (i + 1) % n;
                g.drawLine(xs[i], ys[i], xs[sig], ys[sig]);
            }

            // Nodos
            for (int i = 0; i < n; i++) {
                int id = jugadores.get(i);
                g.setColor(id == actualId ? new Color(0, 120, 215) : new Color(60, 60, 60));
                g.fillOval(xs[i] - r, ys[i] - r, r * 2, r * 2);
                g.setColor(Color.WHITE);
                g.drawString("#" + id, xs[i] - 8, ys[i] + 5);
            }
        }
    }
}