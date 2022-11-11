package tn.esprit.rh.achat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.*;

@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)

@MockitoSettings(strictness = Strictness.LENIENT)
public class TestOperateur {
	@Mock
    OperateurRepository operateurRepo;
    @InjectMocks
    OperateurServiceImpl operateurService;
    Operateur o = new Operateur("nom operateur","prenom operateur","testing",null);
    
    @Test
    public void testAddOperateur() {
        Operateur op = new Operateur("nom operateur","prenom operateur","testing",null);
        Mockito.when(operateurRepo.save(op)).thenReturn(op);
        Operateur oTest = operateurService.addOperateur(op);
        Assertions.assertNotNull(oTest);
    }
    
    @Test
    public void testGetOperateurs() {
    	Mockito.when(operateurRepo.findAll()).thenReturn(Stream
    			.of(new Operateur("nom op1","prenom op1","test1",null), new Operateur("nom op2","prenom op2","test2",null)).collect(Collectors.toList()));
    	Assertions.assertEquals(2,operateurService.retrieveAllOperateurs().size());
    }
	
    @Test
    public void testRetrieveOperateur(){
    	Mockito.when(operateurRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(o));
        Operateur operateurTest = operateurService.retrieveOperateur(o.getIdOperateur());
    }
    
    @Test
    public void testRemoveOperateur(){
    	operateurService.deleteOperateur(o.getIdOperateur());
        Mockito.verify(operateurRepo, Mockito.times(1)).deleteById(o.getIdOperateur()); 
    }
    
    @Test
    public void testModifyOperateur(){
    	 o.setNom("nom updated");
         Mockito.when(operateurRepo.save(o)).thenReturn(o);
         Operateur oTest = operateurService.updateOperateur(o);
         Assertions.assertEquals(o,oTest);
    }
    

}
