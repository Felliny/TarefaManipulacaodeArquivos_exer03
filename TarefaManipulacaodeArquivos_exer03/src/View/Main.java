package View;

import Controller.NetflixController;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        NetflixController netflixController= new NetflixController();

        try {
            netflixController.arquivoMajor_genre();
            netflixController.arquivoPremiere_year();
            netflixController.geraTabela();
            netflixController.buscaSerie(Integer.parseInt(JOptionPane.showInputDialog("Digite o número de estrelas (0-6)")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
