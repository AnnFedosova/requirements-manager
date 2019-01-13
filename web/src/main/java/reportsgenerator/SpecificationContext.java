package reportsgenerator;

import dto.SpecificationDTO;

public class SpecificationContext {
    private String name;


    public SpecificationContext(SpecificationDTO specificationDTO) {
        this.name = specificationDTO.getName();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
