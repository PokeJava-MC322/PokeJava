package application.pokemon;

public enum TipoPokemon {
    BUG,
    DRAGON,
    ELECTRIC,
    FAIRY,
    FIGHTING,
    FIRE,
    GHOST,
    GRASS,
    GROUND,
    ICE,
    NORMAL,
    POISON,
    PSYCHIC,
    ROCK,
    WATER;

    public double efetividade(TipoPokemon tipoPokemon) {
        double[][] matriz = { {1  ,1  ,1  ,0.5,0.5,0.5,0.5,2  ,1  ,1  ,1  ,0.5,2  ,1  ,1  },
                              {1  ,2  ,1  ,0  ,1  ,1  ,1  ,1  ,1  ,1  ,1  ,1  ,1  ,1  ,1  },
                              {1  ,0.5,0.5,1  ,1  ,1  ,1  ,0.5,0  ,1  ,1  ,1  ,1  ,1  ,2  },
                              {1  ,2  ,1  ,1  ,2  ,0.5,1  ,1  ,1  ,1  ,1  ,0.5,1  ,1  ,1  },
                              {0.5,1  ,1  ,0.5,1  ,1  ,0  ,1  ,1  ,2  ,2  ,0.5,0.5,2  ,1  },
                              {2  ,0.5,1  ,1  ,1  ,0.5,1  ,2  ,1  ,2  ,1  ,1  ,1  ,0.5,0.5},
                              {1  ,1  ,1  ,1  ,1  ,1  ,2  ,1  ,1  ,1  ,0  ,1  ,2  ,1  ,1  },
                              {0.5,0.5,1  ,1  ,1  ,0.5,1  ,0.5,2  ,1  ,1  ,0.5,1  ,2  ,2  },
                              {0.5,1  ,2  ,1  ,1  ,2  ,1  ,0.5,1  ,1  ,1  ,2  ,1  ,2  ,1  },
                              {1  ,2  ,1  ,1  ,1  ,0.5,1  ,2  ,2  ,0.5,1  ,1  ,1  ,1  ,0.5},
                              {1  ,1  ,1  ,1  ,1  ,1  ,0  ,1  ,1  ,1  ,1  ,1  ,1  ,0.5,1  },
                              {1  ,1  ,1  ,2  ,1  ,1  ,0.5,2  ,0.5,1  ,1  ,0.5,1  ,0.5,1  },
                              {1  ,1  ,1  ,1  ,2  ,1  ,1  ,1  ,1  ,1  ,1  ,2  ,0.5,1  ,1  },
                              {2  ,1  ,1  ,1  ,0.5,2  ,1  ,1  ,0.5,2  ,1  ,1  ,1  ,1  ,1  },
                              {1  ,0.5,1  ,1  ,1  ,2  ,1  ,0.5,2  ,1  ,1  ,1  ,1  ,2  ,0.5} };
        
        return matriz[this.ordinal()][tipoPokemon.ordinal()];
    }

    public String stringEfetividade(TipoPokemon tipoPokemon) {
        double efetividade = this.efetividade(tipoPokemon);
        if(efetividade == 1)
            return "";
        if(efetividade == 0.5)
            return "O ataque não foi muito efetivo...";
        if(efetividade == 2)
            return "O ataque foi super efetivo!";
        if(efetividade == 0)
            return "O ataque não surtiu efeito...";
        return "";
    }
}
