package darkchoco.essential.company.service.impl;

import darkchoco.essential.company.service.ICompanyService;
import darkchoco.essential.dto.CompanyDto;
import darkchoco.essential.entity.Company;
import darkchoco.essential.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().map(this::convertToDto).toList();
    }

    private CompanyDto convertToDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getLogo(),
                company.getIndustry(),
                company.getSize(),
                company.getRating(),
                company.getLocations(),
                company.getFounded(),
                company.getDescription(),
                company.getEmployees(),
                company.getWebsite(),
                company.getCreatedAt());
    }
}
