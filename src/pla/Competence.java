package pla;

public enum Competence {
	
		MoveRamasse,  //Se déplace vers la pièce la plus proche x
		MoveAttack,	  //Se déplace vers le robot ennemi le plus proche x
		MoveDef,	  //Se déplace vers la base pour la défendre x
		Hit,          //La cible perd des pv x
		Soin,         //Gain de PV x
		Kamikaze,     //Le robot perd tout ses pdv et le perso se trouvant a proximité perd le meme nb de pv x
		AutoDestruction, //Le robot se fait exploser et inflige des degats aux robots sur les 8 cases autour x
		Volvie,       //Vol de vie adversaire, Hit+Soin x
		Stun,		  // permet d'immobiliser la cible (faible chance de fonctionner sinon trop fort)
		AugDef,       //Augmenter resistance robot
		DimDef,       //Diminuer resistance de la cible
		Protect,      //Resister, la cible subit moins de degats que prevu
		Contrer,      //L'attaquant subit sa propre attaque
		Poison,		  // Permet d'empoisonner un adversaire (DoT sur 3 tours)
		Boost,        //Augmenter degats Hit 
		
		/* les opérateurs :  */
		Sup,			// a > b va effectuer a en priorité
		Etoile,			// pendant tout le tour on va eval l'arbre du robot
		
		Ou,				// a | b va faire a ou b (random ou alternance ?)
		 
		
}
