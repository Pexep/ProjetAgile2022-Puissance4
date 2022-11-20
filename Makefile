#Makefile

JAVAC_OPT = \
  -implicit:none \
  -d build -cp "build:res/mariadb-client.jar"\
  -sourcepath "src"
PACKAGE = fr.iutfbleau.ProjetAgile
PACKAGE_PATH = fr/iutfbleau/ProjetAgile

jar : build/${PACKAGE_PATH}/Main.class 
	@jar cfme projet.jar manifest.txt ${PACKAGE}.Main res -C build fr 
	@echo ":: Finish..."
	


#------------------------------------------------------------------------------------Main-----------------------------------------------------------------------------------------

build/${PACKAGE_PATH}/Main.class : src/${PACKAGE_PATH}/Main.java \
						 		   build/${PACKAGE_PATH}/Controleur/Puissance4/ControleurPuissance4.class \
 								   build/${PACKAGE_PATH}/Vue/JFrameJeu.class  \
								   build/${PACKAGE_PATH}/Constant/ConstantesPuissance4.class 
	@echo ":: Main Creating..."		  					
	@javac ${JAVAC_OPT} $< 	
	@echo ":: Main Create..."	
	

#---------------------------------------------------------------------------------Controleur---------------------------------------------------------------------------------------

build/${PACKAGE_PATH}/Controleur/ControleurInterface.class : src/${PACKAGE_PATH}/Controleur/ControleurInterface.java 
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Controleur/Puissance4/ControleurPuissance4.class : src/${PACKAGE_PATH}/Controleur/Puissance4/ControleurPuissance4.java \
																		 build/${PACKAGE_PATH}/Listener/Puissance4/GrilleListener.class \
																		 build/${PACKAGE_PATH}/Listener/Puissance4/ListenerPuissance4.class \
																		 build/${PACKAGE_PATH}/Modele/Puissance4/Modele.class \
																		 build/${PACKAGE_PATH}/Vue/Puissance4/Puissance4.class
	@echo ":: Controleur Creating..."
	@javac ${JAVAC_OPT} $<


#------------------------------------------------------------------------------------Modele-----------------------------------------------------------------------------------------

build/${PACKAGE_PATH}/Modele/Puissance4/Modele.class : src/${PACKAGE_PATH}/Modele/Puissance4/Modele.java \
													   build/${PACKAGE_PATH}/Modele/Puissance4/AbstractModele.class \
													   build/${PACKAGE_PATH}/Listener/Puissance4/GrilleListener.class \
													   build/${PACKAGE_PATH}/Listener/Puissance4/JoueurEvent.class
	@echo ":: Modele Creating..."
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Modele/Puissance4/AbstractModele.class : src/${PACKAGE_PATH}/Modele/Puissance4/AbstractModele.java
	@javac ${JAVAC_OPT} $<
	
#-------------------------------------------------------------------------------------Vue--------------------------------------------------------------------------------------------

build/${PACKAGE_PATH}/Vue/Jeu.class :	src/${PACKAGE_PATH}/Vue/Jeu.java
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Vue/Puissance4/Puissance4.class : src/${PACKAGE_PATH}/Vue/Puissance4/Puissance4.java \
															build/${PACKAGE_PATH}/Vue/Jeu.class \
								  	  			  			build/${PACKAGE_PATH}/Vue/Puissance4/Pion.class
	@javac ${JAVAC_OPT} $<

	
build/${PACKAGE_PATH}/Vue/Puissance4/Pion.class : src/${PACKAGE_PATH}/Vue/Puissance4/Pion.java
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Vue/JFrameJeu.class : src/${PACKAGE_PATH}/Vue/JFrameJeu.java \
											build/${PACKAGE_PATH}/Listener/BoutonListener.class \
 											build/${PACKAGE_PATH}/Vue/Puissance4/Menu/ChoixJeu.class \
											build/${PACKAGE_PATH}/Vue/PanelJeu.class \
											build/${PACKAGE_PATH}/Vue/Puissance4/Menu/Menu.class
	@echo ":: Vue Creating..."	
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Vue/PanelJeu.class : src/${PACKAGE_PATH}/Vue/PanelJeu.java 
	@javac ${JAVAC_OPT} $<



#---------------------------------------Menu------------------------------------------------

build/${PACKAGE_PATH}/Vue/Puissance4/Menu/ChoixJeu.class : src/${PACKAGE_PATH}/Vue/Puissance4/Menu/ChoixJeu.java \
    													   build/${PACKAGE_PATH}/Vue/Puissance4/Menu/ScrollableJPanel.class \
													       build/${PACKAGE_PATH}/Vue/Boutons.class 
	@javac ${JAVAC_OPT} $<


build/${PACKAGE_PATH}/Vue/Puissance4/Menu/ScrollableJPanel.class : src/${PACKAGE_PATH}/Vue/Puissance4/Menu/ScrollableJPanel.java
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Vue/Puissance4/Menu/Menu.class : src/${PACKAGE_PATH}/Vue/Puissance4/Menu/Menu.java
	@javac ${JAVAC_OPT} $<


build/${PACKAGE_PATH}/Vue/Boutons.class : src/${PACKAGE_PATH}/Vue/Boutons.java 
	@javac ${JAVAC_OPT} $<


#-------------------------------------------------------------------------------------Listener---------------------------------------------------------------------------------------


build/${PACKAGE_PATH}/Listener/Puissance4/GrilleListener.class : src/${PACKAGE_PATH}/Listener/Puissance4/GrilleListener.java \
													             build/${PACKAGE_PATH}/Listener/Puissance4/GrilleListenerInterface.class
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Listener/Puissance4/GrilleEvent.class : src/${PACKAGE_PATH}/Listener/Puissance4/GrilleEvent.java
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Listener/Puissance4/GrilleListenerInterface.class : src/${PACKAGE_PATH}/Listener/Puissance4/GrilleListenerInterface.java \
															              build/${PACKAGE_PATH}/Listener/Puissance4/GrilleEvent.class

	@javac ${JAVAC_OPT} $<
	
build/${PACKAGE_PATH}/Listener/BoutonListener.class : src/${PACKAGE_PATH}/Listener/BoutonListener.java \
  													  build/${PACKAGE_PATH}/Controleur/ControleurInterface.class 
	@javac ${JAVAC_OPT} $<
	
build/${PACKAGE_PATH}/Listener/Puissance4/ListenerPuissance4.class : src/${PACKAGE_PATH}/Listener/Puissance4/ListenerPuissance4.java 											  
	@javac ${JAVAC_OPT} $<

build/${PACKAGE_PATH}/Listener/Puissance4/JoueurEvent.class :  src/${PACKAGE_PATH}/Listener/Puissance4/JoueurEvent.java 
	@javac ${JAVAC_OPT} $<


#-----------------------------------------------------------------------------------------Constantes -------------------------------------------------------------------------------

build/${PACKAGE_PATH}/Constant/ConstantesPuissance4.class :  src/${PACKAGE_PATH}/Constant/ConstantesPuissance4.java 
	@javac ${JAVAC_OPT} $<

#-----------------------------------------------------------------------------------------Run & Clean -------------------------------------------------------------------------------

doc : 
	javadoc -d ./doc -sourcepath ./src -subpackages fr -noqualifier java.lang


run : jar
	@echo ":: Executing..."
	@java -jar projet.jar 
	


clean : 
	@echo ":: Cleaning..."
	@rm -rf build/${PACKAGE_PATH}/* \
	rm -rf doc
	@echo ":: Clean"

