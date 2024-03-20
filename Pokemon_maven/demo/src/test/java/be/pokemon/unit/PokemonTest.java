package be.pokemon.unit;

import org.junit.jupiter.api.Test;
import be.pokemon.model.DomainException;
import be.pokemon.model.EeveeEvo.Eevee;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {

    @Test
    void givenValidName_whenSetName_thenNameIsSet() {
        Eevee pokemon = new Eevee("Denis", "eevee", "normal", 5, 0.4, 50.0, 100);
        assertEquals("Denis", pokemon.getName());
    }

    @Test
    void givenNullName_whenSetName_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee(null, "eevee", "normal", 5, 0.4, 50.0, 100));
    }

    @Test
    void givenEmptyName_whenSetName_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("", "eevee", "normal", 5, 0.4, 50.0, 100));
    }

    @Test
    void givenPositiveLevel_whenSetLevel_thenLevelIsSet() {
        Eevee pokemon = new Eevee("Charmander", "eevee", "normal", 5, 0.6, 60.0, 100);
        assertEquals(8, pokemon.levelUpByNumber(3));
    }

    @Test
    void givenNegativeLevel_whenSetLevel_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("Bulbasaur", "eevee", "normal", -3, 0.5, 40.0, 50));
    }

    @Test
    void givenPositiveHeight_whenSetHeight_thenHeightIsSet() {
        Eevee pokemon = new Eevee("Squirtle", "eevee", "normal", 3, 0.5, 40.0, 50);
        assertEquals(0.5, pokemon.getHeight());
    }

    @Test
    void givenNegativeHeight_whenSetHeight_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("Squirtle", "eevee", "normal", 3, -0.5, 40.0, 50));
    }

    @Test
    void givenPositiveHP_whenSetHP_thenHPisSet() {
        Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
        pokemon.setHP(60.0);
        assertEquals(60.0, pokemon.getHP());
    }

    @Test
    void givenNegativeHP_whenSetHP_thenHPisSetToOne() {
        Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
        pokemon.setHP(-20.0);
        assertEquals(1.0, pokemon.getHP());
    }

    @Test
    void givenNullHP_whenSetHP_thenHPisSetToOne() {
        Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
        pokemon.setHP(null);
        assertEquals(1.0, pokemon.getHP());
    }

    @Test
    void givenPositiveXP_whenSetXP_thenXPisSet() {
        Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
        assertEquals(50, pokemon.getXP());
    }

    @Test
    void givenNegativeXP_whenSetXP_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, -50));
    }

    @Test
    void givenPositivePower_whenSetPower_thenPowerIsSet() {
        Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
        assertEquals(50, pokemon.getPower());
    }

    @Test
    void givenNegativePower_whenSetPower_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, -50));
    }

    @Test
    void givenPositiveDamage_whenTakeDamage_thenHPisReduced() {
        Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
        pokemon.takeDamage(20);
        assertEquals(20, pokemon.getHP());
    }

    @Test
    void givenZeroDamage_whenTakeDamage_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
            pokemon.takeDamage(0);
        });
    }

    @Test
    void givenPositiveLevels_whenLevelUpByNumber_thenLevelIsIncreased() {
        Eevee pokemon = new Eevee("Charmander", "eevee", "normal", 5, 0.6, 60.0, 100);
        assertEquals(8, pokemon.levelUpByNumber(3));
    }

    @Test
    void givenNegativeLevels_whenLevelUpByNumber_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
            pokemon.levelUpByNumber(-2);
        });
    }

    void givenPositivePowerIncrease_whenPowerUpByNumber_thenPowerIsIncreased() {
        Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
        assertEquals(60, pokemon.powerUpByNumber(10));
    }

    @Test
    void givenNegativePowerIncrease_whenPowerUpByNumber_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
            pokemon.powerUpByNumber(-10);
        });
    }

    @Test
    void givenValidType_whenSetType_thenTypeIsSet() {
        Eevee pokemon = new Eevee("Charmander", "eevee", "normal", 5, 0.6, 60.0, 100);
        pokemon.setType("Fire");
        assertEquals("Fire", pokemon.getType());
    }

    @Test
    void givenNullType_whenSetType_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
            pokemon.setType(null);
        });
    }

    @Test
    void givenEmptyType_whenSetType_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
            pokemon.setType("");
        });
    }

    @Test
    void givenValidElement_whenSetElement_thenElementIsSet() {
        Eevee pokemon = new Eevee("Charmander", "eevee", "normal", 5, 0.6, 60.0, 100);
        pokemon.setElement("Water");
        assertEquals("Water", pokemon.getElement());
    }

    @Test
    void givenNullElement_whenSetElement_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
            pokemon.setElement(null);
        });
    }

    @Test
    void givenEmptyElement_whenSetElement_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "eevee", "normal", 3, 0.5, 40.0, 50);
            pokemon.setElement("");
        });
    }
}
