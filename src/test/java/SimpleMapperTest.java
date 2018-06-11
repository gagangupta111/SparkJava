import com.dto.InnerDTORequest;
import com.dto.RequestDTO;
import com.mapper.SimpleMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class SimpleMapperTest {

    private SimpleMapper mapper
            = Mappers.getMapper(SimpleMapper.class);

    @Test
    public void givenSourceToDestinationt() {

        InnerDTORequest innerDTORequest = new InnerDTORequest("username", "password");
        RequestDTO requestDTO = mapper.destinationToSource(innerDTORequest).build();

        Assert.assertEquals(innerDTORequest.getUsername(), requestDTO.getUsername());

    }

    @Test
    public void givenDestinationtToSource() {

        InnerDTORequest oneInnerDTORequest = new InnerDTORequest("username", "password");
        RequestDTO requestDTO = mapper.destinationToSource(oneInnerDTORequest).build();

        InnerDTORequest twoInnerDTORequest = mapper.sourceToDestination(requestDTO);

        Assert.assertEquals(oneInnerDTORequest.getUsername(), twoInnerDTORequest.getUsername());


    }

}
