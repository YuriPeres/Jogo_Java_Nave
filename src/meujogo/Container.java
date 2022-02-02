package meujogo;

import javax.swing.JFrame;

import meujogo.Modelo.Fase;

public class Container extends JFrame {
	
	Container() {
		add(new Fase());
		setTitle("JJ Nave");
		setSize(1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Serve para o X fechar o jogo
		setLocationRelativeTo(null); //É o ponto da tela onde quero que comece, null vai dar no centro
		this.setResizable(false); //Se é possível mexer no tamanho da tela, deixar fullscream e talz,
									//no caso deixamos pra não
		setVisible(true); //Aqui define que essa série de comandos seja visível
	}
	
	public static void main (String []args) {
		new Container();
	}

}
