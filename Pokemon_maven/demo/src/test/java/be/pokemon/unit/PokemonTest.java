package be.pokemon.unit;

import org.junit.jupiter.api.Test;

import be.pokemon.model.DomainException;
import be.pokemon.model.EeveeEvo.Eevee;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {

    @Test
    void givenValidName_whenSetName_thenNameIsSet() {
        Eevee pokemon = new Eevee("Denis", "normal", 5, 0.4, 50.0, 100);
        assertEquals("Denis", pokemon.getName());
    }

    @Test
    void givenNullName_whenSetName_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee(null, "normal", 5, 0.4, 50.0, 100));
    }

    @Test
    void givenEmptyName_whenSetName_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("", "normal", 5, 0.4, 50.0, 100));
    }

    @Test
    void givenPositiveLevel_whenSetLevel_thenLevelIsSet() {
        Eevee pokemon = new Eevee("Charmander", "eevee", 5, 0.6, 60.0, 100);
        assertEquals(8, pokemon.levelUpByNumber(3));
    }

    @Test
    void givenNegativeLevel_whenSetLevel_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("Bulbasaur", "normal", -3, 0.5, 40.0, 50));
    }

    @Test
    void givenPositiveHeight_whenSetHeight_thenHeightIsSet() {
        Eevee pokemon = new Eevee("Squirtle", "normal", 3, 0.5, 40.0, 50);
        assertEquals(0.5, pokemon.getHeight());
    }

    @Test
    void givenNegativeHeight_whenSetHeight_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("Squirtle", "normal", 3, -0.5, 40.0, 50));
    }

    @Test
    void givenPositiveHp_whenSetHp_thenHpisSet() {
        Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
        pokemon.sethp(60.0);
        assertEquals(60.0, pokemon.gethp());
    }

    @Test
    void givenNegativeHp_whenSetHp_thenHpisSetToOne() {
        Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
        pokemon.sethp(-20.0);
        assertEquals(1.0, pokemon.gethp());
    }

    @Test
    void givenNullHp_whenSetHp_thenHpisSetToOne() {
        Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
        pokemon.sethp(null);
        assertEquals(1.0, pokemon.gethp());
    }

    @Test
    void givenPositiveXp_whenSetXp_thenXpisSet() {
        Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
        assertEquals(50, pokemon.getxp());
    }

    @Test
    void givenNegativeXp_whenSetXp_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, -50));
    }

    @Test
    void givenPositivePower_whenSetPower_thenPowerIsSet() {
        Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
        pokemon.setPower(50);
        assertEquals(50, pokemon.getPower());
    }

    @Test
    void givenNegativePower_whenSetPower_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, -50));
    }

    @Test
    void givenPositiveDamage_whenTakeDamage_thenHpisReduced() {
        Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
        pokemon.takeDamage(20);
        assertEquals(20, pokemon.gethp());
    }

    @Test
    void givenZeroDamage_whenTakeDamage_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
            pokemon.takeDamage(0);
        });
    }

    @Test
    void givenPositiveLevels_whenLevelUpByNumber_thenLevelIsIncreased() {
        Eevee pokemon = new Eevee("Charmander", "normal", 5, 0.6, 60.0, 100);
        assertEquals(8, pokemon.levelUpByNumber(3));
    }

    @Test
    void givenNegativeLevels_whenLevelUpByNumber_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
            pokemon.levelUpByNumber(-2);
        });
    }

    void givenPositivePowerIncrease_whenPowerUpByNumber_thenPowerIsIncreased() {
        Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
        assertEquals(60, pokemon.powerUpByNumber(10));
    }

    @Test
    void givenNegativePowerIncrease_whenPowerUpByNumber_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
            pokemon.powerUpByNumber(-10);
        });
    }

    @Test
    void givenValidElement_whenSetElement_thenElementIsSet() {
        Eevee pokemon = new Eevee("Charmander", "normal", 5, 0.6, 60.0, 100);
        pokemon.setElement("Water");
        assertEquals("Water", pokemon.getElement());
    }

    @Test
    void givenNullElement_whenSetElement_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
            pokemon.setElement(null);
        });
    }

    @Test
    void givenEmptyElement_whenSetElement_thenThrowsDomainException() {
        assertThrows(DomainException.class, () -> {
            Eevee pokemon = new Eevee("Bulbasaur", "normal", 3, 0.5, 40.0, 50);
            pokemon.setElement("");
        });
    }

}
