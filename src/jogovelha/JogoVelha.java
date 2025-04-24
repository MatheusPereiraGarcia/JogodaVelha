/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jogovelha;

/**
 *
 * @author pgarc
 */
/*
 * Projeto 3: Jogo da Velha com GUI (Swing)
 * Arquivo: JogoDaVelha.java
 * Descrição: Interface 3x3 de botões. Detecta vitória e empate.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoVelha extends JFrame implements ActionListener {
    private JButton[][] botoes = new JButton[3][3];
    private char vez = 'X';

    public JogoVelha() {
        super("Jogo da Velha");
        setLayout(new GridLayout(3, 3));
        initBotoes();
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initBotoes() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton("");
                botoes[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                botoes[i][j].addActionListener(this);
                add(botoes[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (!btn.getText().equals("")) return; // já marcado
        btn.setText(String.valueOf(vez));
        if (verificaVitoria(vez)) {
            JOptionPane.showMessageDialog(this, "Jogador " + vez + " venceu!");
            reinicia();
        } else if (tabuleiroCheio()) {
            JOptionPane.showMessageDialog(this, "Empate!");
            reinicia();
        } else {
            vez = (vez == 'X') ? 'O' : 'X';
        }
    }

    private boolean verificaVitoria(char jogador) {
        // linhas e colunas
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(String.valueOf(jogador)) &&
                botoes[i][1].getText().equals(String.valueOf(jogador)) &&
                botoes[i][2].getText().equals(String.valueOf(jogador))) return true;
            if (botoes[0][i].getText().equals(String.valueOf(jogador)) &&
                botoes[1][i].getText().equals(String.valueOf(jogador)) &&
                botoes[2][i].getText().equals(String.valueOf(jogador))) return true;
        }
        // diagonais
        if (botoes[0][0].getText().equals(String.valueOf(jogador)) &&
            botoes[1][1].getText().equals(String.valueOf(jogador)) &&
            botoes[2][2].getText().equals(String.valueOf(jogador))) return true;
        if (botoes[0][2].getText().equals(String.valueOf(jogador)) &&
            botoes[1][1].getText().equals(String.valueOf(jogador)) &&
            botoes[2][0].getText().equals(String.valueOf(jogador))) return true;
        return false;
    }

    private boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (botoes[i][j].getText().equals("")) return false;
        return true;
    }

    private void reinicia() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                botoes[i][j].setText("");
        vez = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoVelha());
    }
}