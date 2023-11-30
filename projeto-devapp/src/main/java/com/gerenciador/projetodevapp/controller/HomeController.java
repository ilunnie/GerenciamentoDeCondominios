package com.gerenciador.projetodevapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador.projetodevapp.model.ApartmentModel;
import com.gerenciador.projetodevapp.model.BlockModel;
import com.gerenciador.projetodevapp.model.CondominiumModel;
import com.gerenciador.projetodevapp.model.UserModel;
import com.gerenciador.projetodevapp.request.JwtBodyRequest;
import com.gerenciador.projetodevapp.service.ApartmentService;
import com.gerenciador.projetodevapp.service.BlockService;
import com.gerenciador.projetodevapp.service.CondominiumService;
import com.gerenciador.projetodevapp.service.JwtService;
import com.gerenciador.projetodevapp.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("")
public class HomeController {
    @Autowired
    private CondominiumService condominiumService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private UserService userService;

    private JwtService jwt = new JwtService();

    @GetMapping("")
    public List<CondominiumModel> getCondominuims() {
        return condominiumService.findAll();
    }

    @GetMapping("{id}")
    public Optional<CondominiumModel> getCondominuimById(@PathVariable String id) {
        return condominiumService.findById(id);
    }

    @GetMapping("block")
    public List<BlockModel> getBlocks() {
        return blockService.findAll();
    }

    @GetMapping("block/{id}")
    public Optional<BlockModel> getBlockById(@PathVariable String id) {
        return blockService.findById(id);
    }

    @GetMapping("apartment")
    public List<Map<String, Object>> getApartments(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        List<ApartmentModel> list = apartmentService.findWithOffsetAndLimit(offset, limit);
        List<Map<String, Object>> apartments = new ArrayList<>();

        for (ApartmentModel item : list) {
            Map<String, Object> apartment = item.ToMap();

            BlockModel block = blockService.findByApartment(item);
            Map<String, Object> blockMap = new HashMap<>();
            blockMap.put("id", block.getId());
            blockMap.put("name", block.getName());

            CondominiumModel condominium = condominiumService.findByBlock(block);
            Map<String, Object> condominiumMap = new HashMap<>();
            condominiumMap.put("id", condominium.getId());
            condominiumMap.put("name", condominium.getName());
            condominiumMap.put("cep", condominium.getCep());
            condominiumMap.put("address", condominium.getAddress());

            Map<String, Object> localMap = new HashMap<>();
            localMap.put("condominium", condominiumMap);
            localMap.put("block", blockMap);

            apartment.put("local", localMap);
            apartments.add(apartment);
        }

        return apartments;
    }

    @GetMapping("apartment/{id}")
    public Optional<ApartmentModel> getApartmentById(@PathVariable String id) {
        return apartmentService.findById(id);
    }

    @GetMapping("home/{id}")
    public List<ApartmentModel> getApartmentByResident(@PathVariable String id) {
        List<ApartmentModel> apartments = apartmentService.findAll();

        List<ApartmentModel> homes = apartments.stream()
                .filter(apartment -> {
                    boolean isOwner = apartment.getOwner() != null && apartment.getOwner().getIdentity().equals(id);
                    boolean isResident = apartment.getResidents() != null && apartment.getResidents().stream()
                            .anyMatch(resident -> resident.getIdentity().equals(id));
                    return isOwner || isResident;
                })
                .collect(Collectors.toList());

        return homes;
    }

    @PostMapping("")
    public void postCondominuim(@RequestBody CondominiumModel condominium) {
        condominiumService.save(condominium);
    }

    @PostMapping("block/{id}")
    public ResponseEntity<?> postBlock(@RequestBody BlockModel newBlock, @PathVariable String id) {
        var condominium = condominiumService.findById(id);
        if (condominium.isPresent()) {
            var block = blockService.save(newBlock);
            condominiumService.addBlock(condominium.get(), block);

            blockService.save(block);
            condominiumService.save(condominium.get());
            return ResponseEntity.ok().build();
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.NOT_FOUND.value());
            error.put("error", "Condominium not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping("apartment/{id}")
    public ResponseEntity<?> postApartment(@RequestBody ApartmentModel newApartment, @PathVariable String id) {
        var block = blockService.findById(id);

        if (block.isPresent()) {
            var apartment = apartmentService.save(newApartment);
            blockService.addApartment(block.get(), apartment);

            apartmentService.save(apartment);
            blockService.save(block.get());
            return ResponseEntity.ok().build();
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("status", HttpStatus.NOT_FOUND.value());
            error.put("error", "block not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("apartment/{id}")
    public ApartmentModel putApartment(@RequestBody Map<String, Object> body, @PathVariable String id) {
        Optional<ApartmentModel> apartment = apartmentService.findById(id);
        if (!apartment.isPresent()) {
            return null;
        }
        Boolean isAdm = false;
        String token = (String) body.get("token");
        JwtBodyRequest payload = jwt.verifyJwt(token);
        if (payload.getIsAdm() == true) {
            isAdm = true;
        }
        String ownerId = (String) body.get("owner");
        String residentId = (String) body.get("resident");
        Boolean clear = (body.get("clear") != null) ? (Boolean) body.get("clear") : false;
        if (ownerId != null && isAdm) {
            Optional<UserModel> owner = userService.findById(ownerId);
            if (owner.isPresent()) {
                apartment.get().setOwner(owner.get());
            }
        }
        String ownerIdentity = apartment.get().getOwner() != null ? apartment.get().getOwner().getIdentity() : null;
        Boolean isOwner = payload.getIdentity().equals(ownerIdentity);
        if (residentId != null && (isAdm || isOwner)) {
            Optional<UserModel> resident = userService.findById(residentId);
            if (resident.isPresent()) {
                apartmentService.addResident(apartment.get(), resident.get());
            }
        }
        if (clear && isAdm) {
            apartment.get().setOwner(null);
            apartment.get().setResidents(null);
        }

        return apartmentService.save(apartment.get());
    }
}
