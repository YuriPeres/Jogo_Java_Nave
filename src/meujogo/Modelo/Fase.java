package meujogo.Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//
public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer; // Do java.swing
	private List<Inimigo1> inimigo1;
	private List<Estrela> estrelas;
	private boolean emJogo;

	public Fase() {
		// Esses dois é para melhorar o desempenho

		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("res\\blackground.png");
		fundo = referencia.getImage();
		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());

		// Esse timer é a velocidade do jogo
		timer = new Timer(5, this);
		timer.start();

		inicializaInimigos();
		inicializaEstrelas();
		emJogo = true;
	}

	public void inicializaInimigos() {
		int coordenadas[] = new int[40];
		inimigo1 = new ArrayList<Inimigo1>();

		for (int i = 0; i < coordenadas.length - 1; i++) {
			int x = (int) (Math.random() * 4096 + 1024);
			int y = (int) (Math.random() * 698 + 30);
			inimigo1.add(new Inimigo1(x, y));
		}
	}

	public void inicializaEstrelas() {
		int coordenadas[] = new int[100];
		estrelas = new ArrayList<Estrela>();

		for (int i = 0; i < coordenadas.length - 1; i++) {
			int x = (int) (Math.random() * 1024);
			int y = (int) (Math.random() * 728);
			estrelas.add(new Estrela(x, y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;

		if (emJogo) {
			graficos.drawImage(fundo, 0, 0, null); // printa na tela (null para aparecer no meio da tela também

			for (int k = 0; k < estrelas.size(); k++) {
				Estrela q = estrelas.get(k);
				q.load();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}

			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this); // this para ele aparecer nesse
																						// plano

			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) { // o for é para caso atire mais de uma vez antes de sumir o tiro
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int j = 0; j < inimigo1.size(); j++) {
				Inimigo1 in = inimigo1.get(j);
				in.load();

				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		} else {
			ImageIcon fimJogo = new ImageIcon("res\\fimdejogo.png");
			graficos.drawImage(fimJogo.getImage(), 265, 80, null);
		}

		g.dispose(); // dispose = descartar. Fecha uma janela "interna" e não o programa inteiro
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();

		for (int k = 0; k < estrelas.size(); k++) {
			Estrela on = estrelas.get(k);
			if (on.isVisivel()) {
				on.update();
			} else {
				estrelas.remove(k);
			}

		}

		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) { // o for é para caso atire mais de uma vez antes de sumir o tiro
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();

			} else {
				tiros.remove(i);
			}
		}

		for (int j = 0; j < inimigo1.size(); j++) {
			Inimigo1 in = inimigo1.get(j);
			if (in.isVisivel()) {
				in.update();
			} else {
				inimigo1.remove(j);
			}
		}
		checarColisoes();
		repaint(); // repintar a tela, não vai deixar rastros de onde estava
	}

	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaInimigo1;
		Rectangle formaTiro;

		// colisao player e inimigo -> resulta fim de jogo
		for (int i = 0; i < inimigo1.size(); i++) {
			Inimigo1 tempInimigo1 = inimigo1.get(i); // o inimigo temporário é igual ao inimigo dentro do jogo
			formaInimigo1 = tempInimigo1.getBounds();
			if (formaNave.intersects(formaInimigo1)) {
				if (player.isEscudo()) {
					tempInimigo1.setVisivel(false);
				} else {
					player.setVisivel(false);
					tempInimigo1.setVisivel(false);
					emJogo = false;
				}

			}
		}

		//
		List<Tiro> tiros = player.getTiros();
		for (int j = 0; j < tiros.size(); j++) { // retangulo para o tiro
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for (int k = 0; k < inimigo1.size(); k++) { // retangulo para o inimigo
				Inimigo1 tempInimigo1 = inimigo1.get(k);
				formaInimigo1 = tempInimigo1.getBounds();
				if (formaTiro.intersects(formaInimigo1)) {
					tempInimigo1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}

}
