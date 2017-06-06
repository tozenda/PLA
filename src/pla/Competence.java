package pla;

public enum Competence {
	
		Hit,          //La cible perd des pv
		Protect,      //Resister, la cible subit moins de degats que prevu
		Contrer,      //L'attaquant subit sa propre attaque
		Escape,       //Fuire, le robot se deplace vers une case 
		Esquiver,     //Pas de degats subit
		/*Ramasser,     //Ramasser objet
		Deposer,      //Deposer objet*/
		Soin,         //Gain de PV
		Kamikaze,     //Le robot perd tout ses pdv et le perso se trouvant a proximité perd le meme nb de pv
		Ralentir,     //Retrait de PA a la cible
		Porter,       //Porter robot
		DeposerR,     //Deposer robot sur une case 
		AugRes,       //Augmenter resistance robot
		AugPA,        //Augmenter PA robot 
		Volvie,       //Vol de vie adversaire, Hit+Soin
		Invisibilite, //Disparait de la map durant un certain temps de jeu
		Teleporter,   //Teleportation sur une certaine case 
		DimRes,       //Diminuer resistance de la cible
		Pass,         //Faire passer le tour de jeu a la cible
		Sacrif,       //Prendre les degats de l'allié ciblé
		Boost,        //Augmenter degats Hit 
		Piege,        //Deposer un piege sur une case
		Repere,       //Reperage de piege
		
}
