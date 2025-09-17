package nba.league.demo.player;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayersFromTeam(String teamName){
        return playerRepository.findAll()
                .stream()
                .filter(player -> player.getTeam() != null &&
                        player.getTeam().toLowerCase().contains(teamName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPosition(String position){
        return playerRepository.findAll()
                .stream()
                .filter(player -> player.getPos() != null &&
                        player.getPos().toLowerCase().contains(position.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayerByNation(String nation){
        return playerRepository.findAll()
                .stream()
                .filter(player -> player.getNation() != null &&
                        player.getNation().toLowerCase().contains(nation.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayerByTeamAndPosition(String team, String position){
        return playerRepository.findAll()
                .stream()
                .filter(player -> player.getTeam() != null &&
                        player.getTeam().toLowerCase().contains(team.toLowerCase()) &&
                        player.getPos() != null &&
                        player.getPos().toLowerCase().contains(position.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Player> getPlayerByName(String name) {
        return playerRepository.findAll()
                .stream()
                .filter(player -> player.getName() != null &&
                        player.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    public Player addPlayer(Player player) {
        this.playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = this.playerRepository.findByName(updatedPlayer.getName());
        if (existingPlayer.isPresent()) {
            Player playerToUpdate = (Player)existingPlayer.get();
            playerToUpdate.setName(updatedPlayer.getName());
            playerToUpdate.setTeam(updatedPlayer.getTeam());
            playerToUpdate.setPos(updatedPlayer.getPos());
            playerToUpdate.setNation(updatedPlayer.getNation());
            this.playerRepository.save(playerToUpdate);
            return playerToUpdate;
        } else {
            return null;
        }
    }

    @Transactional
    public void deletePlayer(String playerName) {
        this.playerRepository.deleteByName(playerName);
    }

}
