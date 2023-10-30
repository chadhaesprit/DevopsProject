package tn.esprit.devops_project.Invoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.services.OperatorServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class OperatorServiceImplTest {

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorServiceImpl operatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllOperators() {
        // Arrange
        List<Operator> operators = Collections.singletonList(new Operator());
        when(operatorRepository.findAll()).thenReturn(operators);

        // Act
        List<Operator> result = operatorService.retrieveAllOperators();

        // Assert
        assertThat(result).hasSize(1);
    }

    @Test
    void addOperator() {
        // Arrange
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);

        // Act
        Operator result = operatorService.addOperator(operator);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void deleteOperator() {
        // Arrange
        Long id = 1L;

        // Act
        operatorService.deleteOperator(id);

        // Assert
        verify(operatorRepository, times(1)).deleteById(id);
    }

    @Test
    void updateOperator() {
        // Arrange
        Operator operator = new Operator();
        when(operatorRepository.save(operator)).thenReturn(operator);

        // Act
        Operator result = operatorService.updateOperator(operator);

        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void retrieveOperator() {
        // Arrange
        Long id = 1L;
        Operator operator = new Operator();
        when(operatorRepository.findById(id)).thenReturn(Optional.of(operator));

        // Act
        Operator result = operatorService.retrieveOperator(id);

        // Assert
        assertThat(result).isNotNull();
    }
}

