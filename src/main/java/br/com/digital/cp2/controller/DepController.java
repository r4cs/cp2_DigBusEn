package br.com.digital.cp2.controller;

import br.com.digital.cp2.entities.DTO.DepartmentDTO;
import br.com.digital.cp2.entities.Department;
import br.com.digital.cp2.service.DepService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/department")
public class DepController {

    private final DepService service;

    public DepController(@Autowired DepService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid DepartmentDTO dto) {
        Optional<Department> departament = service.cadastrar(dto);
        if (departament.isPresent()) {
            return ResponseEntity.ok(departament);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Object> lerDepPorId(@PathVariable Long id) {
//        return service.lerDepartamentoPorId(id);
        Optional<Department> department = service.lerDepartamentoPorId(id);
        if (department.isPresent()) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Department> lerTdsDep() {
        return service.lerTodosDepartamentos();
    }

    @PatchMapping(value = "/{id}")
    @Transactional
    public void atualizarDpto(@PathVariable Long id, @RequestBody Department department) {
        service.atualizarDep(id, department);
    }

    @DeleteMapping(value = "/{id}")
    public void delDep(@PathVariable  Long id) {
        service.deletarDep(id);
    }
}
