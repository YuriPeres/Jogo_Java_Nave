package meujogo;

import javax.swing.JFrame;

import meujogo.Modelo.Fase;

public class Container extends JFrame {
	
	Container() {
		add(new Fase());
		setTitle("JJ Nave");
		setSize(1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Serve para o X fechar o jogo
		setLocationRelativeTo(null); //� o ponto da tela onde quero que comece, null vai dar no centro
		this.setResizable(false); //Se � poss�vel mexer no tamanho da tela, deixar fullscream e talz,
									//no caso deixamos pra n�o
		setVisible(true); //Aqui define que essa s�rie de comandos seja vis�vel
	}
	
	public static void main (String []args) {
		new Container();
	}

}
