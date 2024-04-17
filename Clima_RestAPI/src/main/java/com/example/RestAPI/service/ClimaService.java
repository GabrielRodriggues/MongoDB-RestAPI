package com.example.RestAPI.service;
import com.example.RestAPI.model.ClimaEntity;
import com.example.RestAPI.repository.ClimaRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClimaService {

    @Autowired
    private ClimaRepository climaRepository;

    public String preverTempo() {
        String dadosMeteorologicos = "";

        String apiUrl = "https://apiadvisor.climatempo.com.br/api/v1/anl/synoptic/locale/BR?token=9fe25332679ebce952fdd9f7f9a83c3e";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dadosMeteorologicos = responseEntity.getBody();
        } else {
            dadosMeteorologicos = "Falha ao obter dados meteorológicos. Código de status: " + responseEntity.getStatusCode();
        }
        inserirDados(dadosMeteorologicos);
        return dadosMeteorologicos;
    }

    public void inserirDados(String dadosMeteorologicos) {
        try {
            JSONArray jsonArray = new JSONArray(dadosMeteorologicos);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                String country = jsonObj.getString("country");
                String date = jsonObj.getString("date");
                String text = jsonObj.getString("text");
                ClimaEntity clima;
                clima = new ClimaEntity();
                clima.setCountry(country);
                clima.setDate(date);
                clima.setText(text);
                climaRepository.save(clima);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private ClimaRepository userRepository;

    public List<ClimaEntity> obterTodos() {
        return userRepository.findAll();
    }

    public ClimaEntity obterPorId(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public ClimaEntity inserir(ClimaEntity user) {
        return userRepository.save(user);
    }

    public ClimaEntity atualizar(String id, ClimaEntity newUser) {
        ClimaEntity existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setCountry(newUser.getCountry());
            existingUser.setDate(newUser.getDate());
            existingUser.setText(newUser.getText());
            return userRepository.save(existingUser);
        } else {
            // Se o usuário não existe:
            return null;
            // Podemos também lançar uma exceção:
            // throw new EntityNotFoundException("Usuário com id " + id + " não encontrado");
        }
    }

    public void excluir(String id) {
        userRepository.deleteById(id);
    }
}