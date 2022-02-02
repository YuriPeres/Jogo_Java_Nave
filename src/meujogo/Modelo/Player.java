package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

//Implementou para poder 
public class Player implements ActionListener {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura; // Para colisão
	private List<Tiro> tiros;
	private boolean isVisivel, isEscudo;
	private Timer timer;

	public Player() {
		// Onde o player vai spawnar
		this.x = 100;
		this.y = 100;
		isVisivel = true;
		isEscudo = false;

		tiros = new ArrayList<Tiro>();

		timer = new Timer(5000, this); // 5000 é equivalente a 5 segs
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isEscudo) {
			escudo();
			isEscudo = false;
		}
		if (isEscudo == false) {
			load();
		}

	}

	public void load() {
		// Define a imagem do player
		ImageIcon referencia = new ImageIcon("res\\spaceship2.png");
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}

	public void update() {
		// Mover o personagem
		x += dx;
		y += dy;
	}

	public void tiroSimples() {
		this.tiros.add(new Tiro(x - 20 + largura, y - 11 + (altura / 2)));
	}

	public void escudo() {
		isEscudo = true;
		ImageIcon referencia = new ImageIcon("res\\naveescudo.png");
		imagem = referencia.getImage();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_A) {
			if (isEscudo == false) {
				tiroSimples();
			}

		}

		if (codigo == KeyEvent.VK_SPACE) {
			escudo();
		}

		if (codigo == KeyEvent.VK_UP) {
			dy = -3;
		}
		if (codigo == KeyEvent.VK_DOWN) {
			dy = 3;
		}
		if (codigo == KeyEvent.VK_LEFT) {
			dx = -3;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
		}
	}

	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public boolean isEscudo() {
		return isEscudo;
	}

	public void setEscudo(boolean isTurbo) {
		this.isEscudo = isTurbo;
	}

}
