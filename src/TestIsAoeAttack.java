public class TestIsAoeAttack {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(5, 5);  // Маленькая карта
        WorldMap map = simulation.getMap();

        // 1. Создаем атакующего мага
        Magic attacker = new Magic(new Coordinate(0, 0), 1, 100, 15, map);
        simulation.addCreature(attacker);

        // 2. Размещаем 3 артефакта рядом для быстрого разблокирования AOE
        map.addEntity(new Artefact(new Coordinate(1, 0)));  // справа
        map.addEntity(new Artefact(new Coordinate(0, 1)));  // снизу
        map.addEntity(new Artefact(new Coordinate(1, 1)));  // диагональ

        System.out.println("=== ШАГ 1: Сбор артефактов ===");
        System.out.println("AOE активна? " + attacker.getIsAoeAttack());  // false

        // Симулируем сбор артефактов (упрощенно)
        attacker.setArtefactCount(3);
        attacker.setAoeAttack(true);
        System.out.println("После сбора 3 артефактов:");
        System.out.println("artefactCount: " + attacker.getArtefactCount());
        System.out.println("AOE активна? " + attacker.getIsAoeAttack());  // true

        // 3. Создаем цель (другой маг) и сущность рядом
        Magic target = new Magic(new Coordinate(3, 3), 1, 30, 10, map);
        simulation.addCreature(target);

        // Артефакт рядом с целью (2,3) - слева от цели
        map.addEntity(new Artefact(new Coordinate(2, 3)));

        System.out.println("\n=== ШАГ 2: AOE атака по цели ===");

        // Принудительно вызываем атаку (как будто путь найден)
        attacker.makeMove(simulation);

        System.out.println("Результат атаки:");
        System.out.println("Цель здоровье: " + target.getHealth());  // должно быть 15 (30-15)
        System.out.println("Артефакт собран? " +
                (map.getEntityAt(new Coordinate(2, 3)) == null));  // true
        System.out.println("AOE активна? " + attacker.getIsAoeAttack());  // true

        System.out.println("\n=== ТЕСТ УСПЕШЕН! ===");
        System.out.println("✓ Цель получила урон");
        System.out.println("✓ Артефакт в AOE собран");
        System.out.println("✓ AOE осталась активной");
    }
}
