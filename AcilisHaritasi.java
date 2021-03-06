import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class AcilisHaritasi extends Haritalar implements ActionListener {
	Timer acilistimer;
	Timer acilistimer2;
	int kontrol=0;
	static boolean  AcilisKontrol1=true,AcilisMap_kontrol=true;
	public AcilisHaritasi() {
		super();
		zamancubugu=new ZamanCubugu();
		hareketsizengel=new HareketsizEngeller[7];
		HareketsizEngelSayisi=hareketsizengel.length;
		hareketsizengel[0]=new HareketsizEngeller(0, 0, 500, 100);
		hareketsizengel[1]=new HareketsizEngeller(0,400,500,75);
		hareketsizengel[2]=new HareketsizEngeller(0,80,80,260);
		hareketsizengel[3]=new HareketsizEngeller(420,0,75,450);
		hareketsizengel[4]=new HareketsizEngeller(150,160,270,20);
		hareketsizengel[5]=new HareketsizEngeller(150,240,270,20);
		hareketsizengel[6]=new HareketsizEngeller(150,320,270,20);
		player=new Player(0, 360, 25, 25);
		acilistimer=new Timer(20, this);
		acilistimer2=new Timer(100, this);
		acilistimer.start();
		acilistimer2.stop();
	}
	@Override
	protected void paintComponent(Graphics g) 
      {
		super.paintComponent(g);
		if(AcilisMap_kontrol)
		{
			 for (int i = 0; i < hareketsizengel.length; i++) 
			 hareketsizengel[i].HareketsizEngelciz(g);	 
			 zamancubugu.zamancubuguCiz(g);
			 player.PlayerCiz(g);
			 repaint();	
		}
     }
	@Override
	public void actionPerformed(ActionEvent arg0) {
			if(AcilisKontrol1)
			{
				player.playerHareket();
				AcilisKontrol1=player.PlayerHareketsizEngelKontrol(0, -adim,hareketsizengel,HareketsizEngelSayisi);
				 if(FareDinleyici.x>150 &&FareDinleyici.x<300)
				 {
					 if(FareDinleyici.y>100 && FareDinleyici.y<200)
					 {
						 System.out.println("Basla");
						 Pencere.label[0].setForeground(Color.WHITE);
						 Pencere.label[1].setForeground(Color.LIGHT_GRAY);
						 Pencere.label[2].setForeground(Color.LIGHT_GRAY);
						 kontrol=1;
						 AcilisKontrol1=false;
						 
					 }
					 else if(FareDinleyici.y>150 && FareDinleyici.y<250)
					 {
						 System.out.println("Bilgi");
						 Pencere.label[0].setForeground(Color.LIGHT_GRAY);
						 Pencere.label[1].setForeground(Color.WHITE);
						 Pencere.label[2].setForeground(Color.LIGHT_GRAY);
					 }
					 else if(FareDinleyici.y>250 && FareDinleyici.y<350)
					 {
						 System.out.println("Ayar");
						 Pencere.label[0].setForeground(Color.LIGHT_GRAY);
						 Pencere.label[1].setForeground(Color.LIGHT_GRAY);
						 Pencere.label[2].setForeground(Color.WHITE);
					 }
					 else
					 {
						 Pencere.label[0].setForeground(Color.LIGHT_GRAY);
						 Pencere.label[1].setForeground(Color.LIGHT_GRAY);
						 Pencere.label[2].setForeground(Color.LIGHT_GRAY);
					 }
				 }
			}
			else
			{
				if(kontrol==0)
				{
					     if (0<FareDinleyici.x && FareDinleyici.x<50 && FareDinleyici.y<400 &&FareDinleyici.y>365) 
						 AcilisKontrol1=true;	 
				         player=new Player(0, 360, 25, 25);
				}
				else if(kontrol==1)
				{
					 acilistimer.stop();
					 player=new Player(0, 0, 0, 0);
					 acilistimer2.start();
					 kontrol=2;
				}
				else if(kontrol==2)
				{
					ZamanCubugu.brenk+=4;
					ZamanCubugu.grenk+=5;
					ZamanCubugu.rrenk+=2;
					zamancubugu.zamancubuguyatay+=10;
					if(zamancubugu.zamancubuguyatay==500)
					{
						zamancubugu.zamancubuguyatay-=10;
						if(LevelGecis.levelgeciskontrol)
						{
							LevelGecis.timerlevelgecis.stop();
							LevelGecis.levelgeciskontrol=false;
							kontrol=3;
							acilistimer2.stop();
							AcilisMap_kontrol=false;
							Pencere.pencere[1].setVisible(true);
							Pencere.pencere[0].dispose();
							Harita1.map1_kontrol=true;
							Harita1.timer1.start();
						}
						else 
						{
							LevelGecis.timerlevelgecis.start();
							Pencere.pencere[6].setVisible(true);
							Pencere.pencere[0].setVisible(false);
						}
					}
				}	 
			}
	}
}

