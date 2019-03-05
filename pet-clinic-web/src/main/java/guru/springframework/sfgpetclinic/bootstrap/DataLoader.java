package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Ofer");
        owner1.setLastName("Cohen");
        owner1.setAddress("12 Rabin St.");
        owner1.setCity("Rosh Haayn");
        owner1.setTelephone("09534546");

        Pet ofersCat = new Pet();
        ofersCat.setPetType(savedCatPetType);
        ofersCat.setOwner(owner1);
        ofersCat.setBirthDate(LocalDate.now());
        ofersCat.setName("Mitsy");

        owner1.getPets().add(ofersCat);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Ron");
        owner2.setLastName("Federman");
        owner2.setAddress("13 Mishol Gil St.");
        owner2.setCity("Kfar Sava");
        owner2.setTelephone("09514346");

        Pet ronsDog = new Pet();
        ronsDog.setName("Chuck");
        ronsDog.setOwner(owner2);
        ronsDog.setBirthDate(LocalDate.now());
        ronsDog.setPetType(savedDogPetType);
        owner2.getPets().add(ronsDog);

        ownerService.save(owner2);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Snoop");
        vet1.setLastName("Dogg");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mary");
        vet2.setLastName("Poppins");

        vetService.save(vet2);

        System.out.println("loaded vets..");

    }
}
