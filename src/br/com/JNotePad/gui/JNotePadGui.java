package br.com.JNotePad.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import br.com.JNotePad.util.EscreverTXT;

public class JNotePadGui extends JFrame {
	
/**
 * 
 */
private static final long serialVersionUID = 1L;
private JMenuBar jmenuPrincipal;
private JMenu jMenu;
private JMenuItem jmiAbrir;
private JMenuItem jmiNovo;
private JMenuItem jmiSalvar;
private JMenuItem jmiSair;
private JTextArea jTxArea;
	
	public JNotePadGui(){
		super("JNotePad - Editor de texo");
		
		this.getContentPane();
		
		jmenuPrincipal = new JMenuBar();
		jMenu          = new JMenu("Arquivo");
		jmiNovo  	   = new JMenuItem("Novo");	
		jmiAbrir       = new JMenuItem("Abrir");
		jmiSair        = new JMenuItem("Sair");
		jmiSalvar      = new JMenuItem("Salvar");
		jTxArea        = new JTextArea();
		
		jmenuPrincipal.add(jMenu);
		jMenu.add(jmiNovo);
		jMenu.add(jmiAbrir);
		jMenu.add(jmiSalvar);
		jMenu.addSeparator();
		jMenu.add(jmiSair);
		
		this.setJMenuBar(jmenuPrincipal);
		
		this.getContentPane().add(jTxArea);

		jmiSair.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sairOnClick();
			}
		});
		
		jmiNovo.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				novoOnClick();
			}
		});
		
		jmiAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirOnClick();
			}
		});
		
		jmiSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				salavarOnClick();
			}
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(640, 480);
		
	}

	public static void main (String Args[]){
		 JNotePadGui notepad = new JNotePadGui();
		 
		 try{                   
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch(Exception e){
	          	System.out.println("Não foi possível alterar a L&F");
	        }
	}

	void sairOnClick() {
		System.exit(0);		
	}
	
	void novoOnClick(){				
		limparCampos();
	}

	 void limparCampos() {
		jTxArea.setText("");
	}

	void abrirOnClick() {
		try {
				JFileChooser jFileAquivo = new JFileChooser();
				int opcao                = jFileAquivo.showOpenDialog(null);
				String path;
				String conteudo;
				
				if (opcao == JFileChooser.APPROVE_OPTION){
					path = jFileAquivo.getSelectedFile().getAbsolutePath();
					conteudo = EscreverTXT.abrirArquivoTXT(path);
					jTxArea.setText(conteudo);
				}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Arquivo não existi \n"+ e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Erro \n"+ e.getMessage());
		}
	}
	 
	void salavarOnClick() {
		try {
			JFileChooser jFileAquivo = new JFileChooser();
			int opcao                = jFileAquivo.showSaveDialog(null);
			String path;
			String conteudo;
			boolean retorno;
			
			if (opcao == JFileChooser.APPROVE_OPTION){
				path = jFileAquivo.getSelectedFile().getAbsolutePath();
				conteudo = jTxArea.getText();
			 retorno = EscreverTXT.persistirArquivoTXT(path, conteudo);
			 if (retorno) {
				 JOptionPane.showMessageDialog(this, "Arquivo, salvo com sucesso !!!");
			 }
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro \n"+ e.getMessage());
		}
	}
			
}
